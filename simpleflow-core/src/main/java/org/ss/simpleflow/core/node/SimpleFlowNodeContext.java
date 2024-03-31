package org.ss.simpleflow.core.node;

public interface SimpleFlowNodeContext {
    SimpleFlowNodeConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
