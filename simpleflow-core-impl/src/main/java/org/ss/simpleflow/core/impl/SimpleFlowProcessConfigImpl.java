package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowProcessConfig;

import java.util.Set;

public class SimpleFlowProcessConfigImpl implements SimpleFlowProcessConfig {

    private String id;

    private String name;

    private String description;

    private Set<SimpleFlowNodeConfigImpl> nodeConfigSet;

    private Set<SimpleFlowLineConfigImpl> lineConfigSet;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Set<SimpleFlowNodeConfigImpl> getNodeConfigSet() {
        return nodeConfigSet;
    }

    public void setNodeConfigSet(Set<SimpleFlowNodeConfigImpl> nodeConfigSet) {
        this.nodeConfigSet = nodeConfigSet;
    }

    @Override
    public Set<SimpleFlowLineConfigImpl> getLineConfigSet() {
        return lineConfigSet;
    }

    public void setLineConfigSet(Set<SimpleFlowLineConfigImpl> lineConfigSet) {
        this.lineConfigSet = lineConfigSet;
    }
}
