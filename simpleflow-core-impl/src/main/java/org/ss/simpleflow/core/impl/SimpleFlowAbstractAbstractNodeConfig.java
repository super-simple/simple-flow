package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.component.SimpleFlowAbstractComponentConfig;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public abstract class SimpleFlowAbstractAbstractNodeConfig extends SimpleFlowAbstractComponentConfig implements SimpleFlowNodeConfig {

    protected String nodeType;

    protected String eventCode;

    protected String eventType;

    void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    void setEventType(String eventType) {
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
