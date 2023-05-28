package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractNode;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowNodeFactory {

    SimpleFlowAbstractNode getNode(String processId, String nodeId, String code, SimpleFlowNodeConfig simpleFlowNodeConfig);
}
