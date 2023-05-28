package org.ss.simpleflow.core.impl;


import org.ss.simpleflow.core.node.SimpleFlowAbstractNodeConfig;
import org.ss.simpleflow.core.node.SimpleFlowNode;

public abstract class SimpleFlowAbstractNode extends SimpleFlowAbstractComponent implements SimpleFlowNode {

    private SimpleFlowAbstractNodeConfig config;


    void setConfig(SimpleFlowAbstractNodeConfig config) {
        this.config = config;
    }

    @Override
    public SimpleFlowAbstractNodeConfig getConfig() {
        return config;
    }
}
