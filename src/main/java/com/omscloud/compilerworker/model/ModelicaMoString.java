package com.omscloud.compilerworker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ModelicaMoString {
    private String upname;
    private String metahash;
    private String moFile;

    public String getmetahash(){
        return metahash;
    }

    public String getMoFile() {
        return moFile;
    }
}
