package com.omscloud.compilerworker.model;

import java.util.List;

public class ModelJson {
    private String name;
    private List<Component> components;
    private List<Connection> connections;

    public String getName() {
        return name;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
