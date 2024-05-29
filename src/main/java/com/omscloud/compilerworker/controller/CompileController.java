package com.omscloud.compilerworker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.omscloud.compilerworker.service.ICompileService;
import com.omscloud.compilerworker.service.IMoTransfer;
import com.omscloud.compilerworker.service.ISaveMoFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.omscloud.compilerworker.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@RestController
@RequestMapping("/compile")
public class CompileController {

 //   @Autowired
 //   private ICompileService compileService;
    @Autowired
    private IMoTransfer moTransfer;
    @Autowired
    private ISaveMoFile saveMoFile;
/*
    @PostMapping("/exec")
    public Map<String, Object> exec(@RequestBody ModelicaMoString mms) {
        return compileService.execMoFile(mms).build();
    }
*/
    @PostMapping("/savemojson")
    public ResponseEntity<Map<String, String>> handlePostMoFile(@RequestBody Map<String, Object> requestBody) {
        // 在这里处理数据
        System.out.println("Body Data: " + requestBody);  // 显示body数据
        ModelJson model=new ModelJson();
        ModelConfig modelConfig=new ModelConfig();
        try {
            Object configObject = requestBody.get("modelconfig");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

            String configJson = objectMapper.writeValueAsString(configObject);
            modelConfig=objectMapper.readValue(configJson, ModelConfig.class);

            System.out.println("ModelConfig:" + modelConfig.toString());

        }catch (Exception e) {
            // 处理转换异常
            e.printStackTrace();
        }
        try {
            Object modelObject = requestBody.get("model");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            String modelJson = objectMapper.writeValueAsString(modelObject);
            model=objectMapper.readValue(modelJson, ModelJson.class);
            System.out.println("Model:" + model.toString());

        }catch (Exception e) {
            // 处理转换异常
            e.printStackTrace();
        }


        System.out.println(moTransfer.generateMoFileContent(model));
        

        //boolean save=saveMoFile.saveMoFile(requestBody.toString());
       // System.out.println(save);

        Map<String, String> response = Map.of("message", "Data received successfully");
        return ResponseEntity.ok(response);
    }


}
