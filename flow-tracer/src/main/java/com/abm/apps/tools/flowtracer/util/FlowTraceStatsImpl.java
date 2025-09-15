package com.abm.apps.tools.flowtracer.util;

import com.abm.apps.tools.flowtracer.pojo.FlowTrace;
import com.abm.apps.tools.flowtracer.pojo.FlowTraceChain;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class FlowTraceStatsImpl implements FlowTraceStats {

  @Override
  public void printFlowTraceStatistics(FlowTraceChain flowTraceChain) {
    printToLogger(flowTraceChain, log);
  }

  @Override
  public void printFlowTraceStatistics(FlowTraceChain flowTraceChain, Logger otherLogger) {
    printToLogger(flowTraceChain, otherLogger);
  }

  private void printToLogger(FlowTraceChain flowTraceChain, Logger logger) {
    try {
      logger.info(
          "Printing Flow-Trace chain statistics; FlowTrace chain != null : {}",
          flowTraceChain != null);
      logger.info(
          "FlowTrace chain size = {}; Trace started at : {}",
          flowTraceChain.getFlowTraces().size(),
          new Date(flowTraceChain.getTimestamp()));
      logger.info("Start-Chain----------------------");
      List<FlowTrace> flows = flowTraceChain.getFlowTraces();
      long currentTimestamp = 0L;
      for (int i = 0; i < flowTraceChain.getFlowTraces().size(); i++) {
        FlowTrace ft = flows.get(i);
        long timestamp = ft.getTimestamp() == 0L ? -1L : ft.getTimestamp();
        logger.info(
            "Application = [{}], step = [{}], timestamp = [{}], timestamp-date =[{}], time passed after previous step = [{}]ms",
            ft.getAppName(),
            ft.getStepName(),
            timestamp,
            timestamp == -1L ? timestamp : new Date(timestamp),
            i == 0
                ? (flowTraceChain.getTimestamp() - ft.getTimestamp())
                : (ft.getTimestamp() - flows.get(i - 1).getTimestamp()));
      }
      logger.info("End-Chain----------------------");
    } catch (Exception e) {
      logger.error("Error when attempting to print Flow Trace statistics", e);
    }
  }
}
