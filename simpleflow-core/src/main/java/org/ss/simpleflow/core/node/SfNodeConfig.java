package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponentConfig;

import java.util.Map;
import java.util.Set;

public interface SfNodeConfig<NI, PCI> extends SfComponentConfig {

    NI getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    long getMaxLoopCount();

    boolean isResultNode();

    Map<String, SfNodeParameter> getParameterMap();

    Map<String, SfNodeResult> getResultMap();

    Set<String> getEnumGatewayEnumSet();

    PCI getProcessId();
}