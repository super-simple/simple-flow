package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponentConfig;

import java.util.Set;

public interface SfNodeConfig<NI, PCI> extends SfComponentConfig {

    NI getId();

    String getNodeType();

    String getEventCode();

    String getEventType();

    long getMaxExecuteCount();

    boolean isResultNode();

    SfNodeParameter[] getParameter();

    SfNodeResult[] getResult();

    Set<String> getEnumGatewayEnumSet();

    PCI getProcessId();
}