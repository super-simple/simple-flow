package org.ss.simpleflow.core.impl.simple;

import org.ss.simpleflow.core.impl.simple.line.SimpleFlowSimpleLineConfig;
import org.ss.simpleflow.core.impl.simple.node.SimpleFlowSimpleNodeConfig;
import org.ss.simpleflow.core.processconfig.SimpleFlowAbstractProcessConfig;

import java.util.Set;

public class SimpleFlowSimpleProcessConfig extends SimpleFlowAbstractProcessConfig {

    private Set<SimpleFlowSimpleNodeConfig> nodeConfigSet;
    private Set<SimpleFlowSimpleLineConfig> lineConfigSet;

    @Override
    public Set<SimpleFlowSimpleNodeConfig> getNodeConfigSet() {
        return nodeConfigSet;
    }

    public void setNodeConfigSet(Set<SimpleFlowSimpleNodeConfig> nodeConfigSet) {
        this.nodeConfigSet = nodeConfigSet;
    }

    @Override
    public Set<SimpleFlowSimpleLineConfig> getLineConfigSet() {
        return lineConfigSet;
    }

    public void setLineConfigSet(Set<SimpleFlowSimpleLineConfig> lineConfigSet) {
        this.lineConfigSet = lineConfigSet;
    }
}
