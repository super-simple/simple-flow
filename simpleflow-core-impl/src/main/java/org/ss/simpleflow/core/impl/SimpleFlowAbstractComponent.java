package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowContext;
import org.ss.simpleflow.core.component.SimpleFlowComponent;

public abstract class SimpleFlowAbstractComponent implements SimpleFlowComponent {

    protected String id;

    protected String code;

    protected String name;

    protected String description;

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
    public SimpleFlowContext getContext() {
        return context;
    }
}
