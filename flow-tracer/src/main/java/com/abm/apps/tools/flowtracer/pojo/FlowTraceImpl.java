package com.abm.apps.tools.flowtracer.pojo;

import lombok.Builder;

@Builder
public class FlowTraceImpl implements FlowTrace {


    private String appName = "default";
    private String stepName;
    private long timestamp = 0L;


    @Override
    public String getAppName() {
        return this.appName;
    }

    @Override
    public String getStepName() {
        return this.stepName;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

}
