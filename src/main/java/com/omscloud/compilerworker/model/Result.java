package com.omscloud.compilerworker.model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Map<String, Object> data=new HashMap<>();
    public static final int isok=200;
    private Object body;

    public Result addlog(String log){
        data.put("execlog",log);
        return this;
    }
    public Result addanswer(String csvfile){
        data.put("answer",csvfile);
        return this;
    }
    public Result addcode(int status){
        data.put("code",status);
        return this;
    }
    @Override
    public String toString() {
        return  data.toString();
    }

    public Map<String, Object> build() {
        if(!data.containsKey("code")){
            data.put("code",200);
        }
        return data;
    }

}
