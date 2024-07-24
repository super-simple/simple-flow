package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponentConfig;

import java.util.Map;
import java.util.Set;

public interface SfNodeConfig<NODE_ID, PROCESS_CONFIG_ID> extends SfComponentConfig {

    NODE_ID getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    long getMaxLoopCount();

    boolean isResultNode();

    Map<String, SfNodeParameter> getParameterMap();

    Map<String, SfNodeResult> getResultMap();

    Set<String> getEnumGatewayEnumSet();

    PROCESS_CONFIG_ID getProcessId();
}