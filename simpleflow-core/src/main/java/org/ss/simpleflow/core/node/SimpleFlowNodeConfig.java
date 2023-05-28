package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SimpleFlowComponentConfig;

public interface SimpleFlowNodeConfig extends SimpleFlowComponentConfig {

    String getNodeType();

    String getEventCode();

    String getEventType();
}