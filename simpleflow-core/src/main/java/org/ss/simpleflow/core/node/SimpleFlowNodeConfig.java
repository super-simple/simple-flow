package org.ss.simpleflow.core.node;

import java.util.Map;
import java.util.Set;

public interface SimpleFlowNodeConfig {

    String getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    long getMaxLoopCount();

    boolean resultNode();

    Map<String, SimpleFlowNodeParameter> getParameterMap();

    Map<String, SimpleFlowNodeResult> getResultMap();

    Set<String> getGatewayEnumSet();
}