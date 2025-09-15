package com.abm.apps.tools.flowtracer.pojo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FlowTraceChain {

  private List<FlowTrace> flowTraces = new ArrayList<>();
  private long timestamp;
  private final String defaultStepname = "[a null FlowTrace was provided]";

  public FlowTraceChain() {
    this.timestamp = System.currentTimeMillis();
  }

  public void addFlowTrace(String stepName) {
    flowTraces.add(FlowTraceImpl.builder().stepName(stepName).build());
  }

  public void addFlowTraceWithTimestamp(String stepName) {
    flowTraces.add(
        FlowTraceImpl.builder().stepName(stepName).timestamp(System.currentTimeMillis()).build());
  }

  public void addFlowTrace(String appName, String stepName) {
    flowTraces.add(FlowTraceImpl.builder().appName(appName).stepName(stepName).build());
  }

  public void addFlowTraceWithTimestamp(String appName, String stepName) {
    flowTraces.add(
        FlowTraceImpl.builder()
            .appName(appName)
            .stepName(stepName)
            .timestamp(System.currentTimeMillis())
            .build());
  }

  public List<FlowTrace> getFlowTraces() {
    return this.flowTraces;
  }

  public long getTimestamp() {
    return this.timestamp;
  }
}
