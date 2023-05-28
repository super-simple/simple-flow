package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowNode;
import org.ss.simpleflow.core.SimpleFlowNodeFactory;

public class SimpleFlowNodeFactoryImpl implements SimpleFlowNodeFactory {

    @Override
    public SimpleFlowNode getNode(String processId, String nodeId) {
        return new SimpleFlowNodePrintlnImpl();
    }

}
