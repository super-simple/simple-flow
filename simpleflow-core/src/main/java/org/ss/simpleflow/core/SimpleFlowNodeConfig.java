package org.ss.simpleflow.core;

public interface SimpleFlowNodeConfig extends SimpleFlowComponentConfig {

    String getNodeType();

    String getEventCode();

    String getEventType();
}