package org.ss.simpleflow.core.node;

public interface SfNodeContext {
    SfNodeConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
