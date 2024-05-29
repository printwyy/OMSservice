package com.omscloud.compilerworker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;

public class Component {
    @JsonProperty("URI")
    private String uri;

    private String name;
    private Map<String, String> parameters=Collections.emptyMap();
    private Map<String, String> modifiers=Collections.emptyMap();

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    public void setModifiers(Map<String, String> modifiers) {
        this.modifiers = modifiers;
    }
    public String getName() {
        return name;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
    public Map<String, String> getModifiers() {
        return modifiers;
    }
    public String toString()
    {
        return "Component{" +
                "uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", parameters=" + parameters +
                ", modifiers=" + modifiers +
                '}';
    }
}
