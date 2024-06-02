package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponentConfig;

import java.util.Map;
import java.util.Set;

public interface SfNodeConfig extends SfComponentConfig {

    String getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    Long getMaxLoopCount();

    Boolean resultNode();

    Map<String, SfNodeParameter> getParameterMap();

    Map<String, SfNodeResult> getResultMap();

    Set<String> getGatewayEnumSet();
}