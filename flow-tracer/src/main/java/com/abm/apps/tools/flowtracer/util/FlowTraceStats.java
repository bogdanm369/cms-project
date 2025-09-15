package com.abm.apps.tools.flowtracer.util;

import com.abm.apps.tools.flowtracer.pojo.FlowTraceChain;
import org.slf4j.Logger;

public interface FlowTraceStats {

    public void printFlowTraceStatistics(FlowTraceChain flowTraceChain);

    public void printFlowTraceStatistics(FlowTraceChain flowTraceChain, Logger logger);
}
