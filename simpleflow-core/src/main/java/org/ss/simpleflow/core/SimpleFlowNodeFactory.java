package org.ss.simpleflow.core;

public interface SimpleFlowNodeFactory {

    SimpleFlowNode getNode(String processId, String nodeId);
}
