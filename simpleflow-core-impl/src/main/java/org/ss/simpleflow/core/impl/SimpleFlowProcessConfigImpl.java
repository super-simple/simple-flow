package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowLineConfig;
import org.ss.simpleflow.core.SimpleFlowNodeConfig;
import org.ss.simpleflow.core.SimpleFlowProcessConfig;

import java.util.HashSet;
import java.util.Set;

public class SimpleFlowProcessConfigImpl implements SimpleFlowProcessConfig {

    private String id;

    private String name;

    private String description;

    private Set<SimpleFlowNodeConfig> nodeConfigSet = new HashSet<>();

    private Set<SimpleFlowLineConfig> lineConfigSet = new HashSet<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNodeConfigSet(Set<SimpleFlowNodeConfig> nodeConfigSet) {
        this.nodeConfigSet = nodeConfigSet;
    }

    public void setLineConfigSet(Set<SimpleFlowLineConfig> lineConfigSet) {
        this.lineConfigSet = lineConfigSet;
    }

    @Override
    public String getId() {
        return id;
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
    public Set<SimpleFlowNodeConfig> getNodeConfigSet() {
        return nodeConfigSet;
    }

    @Override
    public Set<SimpleFlowLineConfig> getLineConfigSet() {
        return lineConfigSet;
    }
}
