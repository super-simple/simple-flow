package org.ss.simpleflow.core.component;

import org.ss.simpleflow.core.SimpleFlowContext;

public abstract class SimpleFlowAbstractComponent implements SimpleFlowComponent {

    protected String id;

    protected String code;

    protected String name;

    protected String description;

    protected SimpleFlowComponentConfig config;

    protected SimpleFlowContext context;

    void setId(String id) {
        this.id = id;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
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
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
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
