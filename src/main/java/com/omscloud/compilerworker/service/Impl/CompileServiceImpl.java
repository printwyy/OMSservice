package com.omscloud.compilerworker.service.Impl;

import com.omscloud.compilerworker.model.ModelicaMoString;
import com.omscloud.compilerworker.model.Result;
import com.omscloud.compilerworker.service.ICompileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class CompileServiceImpl implements ICompileService {

    static final String mopath="/Users/mac/Desktop/datavolume";

    @Override
    public Result execMoFile(ModelicaMoString mms){
        Result result = new Result();

        String filepath=mopath+"/"+mms.getmetahash()+"/"+mms.getmetahash()+".mo";
        File dir = new File(filepath).getParentFile();

        // 创建所有父目录，如果它们不存在
        if (!dir.exists() && !dir.mkdirs()) {
            // 创建目录失败，设置错误信息并返回

            result.addcode(500);
            log.info("创建目录失败");
            return result;
        }

        try (FileWriter writer = new FileWriter(new File(mopath))) {
            // 将mms字符串内容写入到文件
            writer.write(mms.getMoFile());

            // 如果写入成功，可以设置result的相关状态来表示操作成功
            log.info("写入文件成功");
        } catch (IOException e) {
            // 写入失败时，处理异常，并设置result的状态以反映错误
            result.addcode(500);
            log.error("写入文件失败",e);
        }
        String dirpath="/modata"+"/"+mms.getmetahash();

        String[] commands={"docker exec -it dssworker /bin/sh","omc -s"+mms.getmetahash()+".mo Modelica",
                "make -f "+mms.getmetahash()+".makefile","./"+mms.getmetahash()+" -override=outputFormat=csv -lv=LOG_STATS"};
        for(String command:commands){
            try {
                // 使用ProcessBuilder来构建进程，可以在指定目录下执行命令

                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                // 设置工作目录
                processBuilder.directory(new File(dirpath));

                // 启动进程
                Process process = processBuilder.start();
                BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String line=null;

                while((line=reader.readLine())!=null){
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }

                // 可以进一步处理进程的输出流、错误流等，这里简单示例不展开
                int exitCode = process.waitFor(); // 等待进程结束
                if (exitCode == 0) {
                    System.out.println("Command executed successfully.");
                    System.out.println(sb.toString());
                    log.info(command+"执行成功");
                    log.info(sb.toString());
                } else {
                    log.info(command+"执行失败");
                    System.err.println("Command execution failed with exit code: " + exitCode);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                log.info("命令执行失败");
                result.addcode(501);
            }
        }
        String answercsv=mopath+"/"+mms.getmetahash()+"_res.csv";

        return result;
    }
}
