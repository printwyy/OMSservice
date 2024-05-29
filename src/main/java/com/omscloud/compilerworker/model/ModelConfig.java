package com.omscloud.compilerworker.model;

public class ModelConfig {
    private String startTime;
    private String stopTime;
    private String stepSize;
    private String tolerance;
    private String solver;
    private String outputFormat;
    private String variableFilter;

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStepSize(String stepSize) {
        this.stepSize = stepSize;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public void setVariableFilter(String variableFilter) {
        this.variableFilter = variableFilter;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public String getSolver() {
        return solver;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStepSize() {
        return stepSize;
    }
    public String getStopTime() {
        return stopTime;
    }
    public String getTolerance() {
        return tolerance;
    }
    public String getVariableFilter() {
        return variableFilter;
    }
    public String toString()
    {
        return "ModelConfig{" +
                "startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", stepSize='" + stepSize + '\'' +
                ", tolerance='" + tolerance + '\'' +
                ", solver='" + solver + '\'' +
                ", outputFormat='" + outputFormat + '\'' +
                ", variableFilter='" + variableFilter + '\'' +
                '}';
    }
}
