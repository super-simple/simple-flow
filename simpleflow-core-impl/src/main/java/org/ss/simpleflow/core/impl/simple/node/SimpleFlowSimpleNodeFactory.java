package org.ss.simpleflow.core.impl.simple.node;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractNode;
import org.ss.simpleflow.core.impl.factory.SimpleFlowNodeFactory;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public class SimpleFlowSimpleNodeFactory implements SimpleFlowNodeFactory {

    @Override
    public SimpleFlowAbstractNode getNode(String processId, String nodeId, String code, SimpleFlowNodeConfig simpleFlowNodeConfig) {
        return new SimpleFlowSimpleNode();
    }

}
