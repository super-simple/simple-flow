package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowComponent;
import org.ss.simpleflow.core.SimpleFlowComponentConfig;
import org.ss.simpleflow.core.SimpleFlowContext;

public abstract class SimpleFlowAbstractComponentImpl implements SimpleFlowComponent {

    private String id;

    private SimpleFlowComponentConfig config;

    private SimpleFlowContext context;


    void setId(String id) {
        this.id = id;
    }

    void setConfig(SimpleFlowComponentConfig config) {
        this.config = config;
    }

    void setContext(SimpleFlowContext context) {
        this.context = context;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SimpleFlowComponentConfig getConfig() {
        return config;
    }

    @Override
    public SimpleFlowContext getContext() {
        return context;
    }
}
