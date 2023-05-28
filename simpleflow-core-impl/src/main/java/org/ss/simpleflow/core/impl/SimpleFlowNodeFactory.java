package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.node.SimpleFlowAbstractNode;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowNodeFactory {

    SimpleFlowAbstractNode getNode(String processId, String nodeId, String code, SimpleFlowNodeConfig simpleFlowNodeConfig);
}
