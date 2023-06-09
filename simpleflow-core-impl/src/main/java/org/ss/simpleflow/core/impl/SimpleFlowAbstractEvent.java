package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.event.SimpleFlowEvent;
import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;

public abstract class SimpleFlowAbstractEvent extends SimpleFlowAbstractComponent implements SimpleFlowEvent {

    private SimpleFlowAbstractNodeConfig config;


    void setConfig(SimpleFlowAbstractNodeConfig config) {
        this.config = config;
    }

    @Override
    public SimpleFlowAbstractNodeConfig getConfig() {
        return config;
    }
}
