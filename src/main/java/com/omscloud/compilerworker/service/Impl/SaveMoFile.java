package com.omscloud.compilerworker.service.Impl;
import com.omscloud.compilerworker.model.ModelicaMoString;
import com.omscloud.compilerworker.model.Result;
import com.omscloud.compilerworker.service.ICompileService;
import com.omscloud.compilerworker.service.ISaveMoFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class SaveMoFile implements ISaveMoFile {
    static final String mopath="/Users/mac/Desktop/datavolume";

    @Override
    public boolean saveMoFile(String mofile)
    {
        String filepath=mopath+"/"+"test.mo";
        File dir = new File(filepath).getParentFile();

        // 创建所有父目录，如果它们不存在
        if (!dir.exists() && !dir.mkdirs()) {
            // 创建目录失败，设置错误信息并返回
            log.info("创建目录失败");
            return false;
        }

        try (FileWriter writer = new FileWriter(new File(mopath))) {
            // 将mms字符串内容写入到文件
            writer.write(mofile);

            // 如果写入成功，可以设置result的相关状态来表示操作成功
            log.info("写入文件成功");
        } catch (IOException e) {
            // 写入失败时，处理异常，并设置result的状态以反映错误

            log.error("写入文件失败",e);
            return false;
        }
        return true;
    }
}
