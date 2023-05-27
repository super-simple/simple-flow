package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowNodeConfig;

public class SimpleFlowNodeConfigImpl extends SimpleFlowComponentConfigImpl implements SimpleFlowNodeConfig {

    private String nodeType;

    private String eventCode;

    private String eventType;

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public String getEventCode() {
        return eventCode;
    }

    @Override
    public String getEventType() {
        return eventType;
    }
}
