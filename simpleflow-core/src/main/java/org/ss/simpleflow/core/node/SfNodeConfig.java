package org.ss.simpleflow.core.node;

import java.util.Map;
import java.util.Set;

public interface SfNodeConfig {

    String getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    long getMaxLoopCount();

    boolean resultNode();

    Map<String, SfNodeParameter> getParameterMap();

    Map<String, SfNodeResult> getResultMap();

    Set<String> getGatewayEnumSet();
}