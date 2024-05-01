package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfNodeConfig;

import java.io.Serializable;

public interface SfNodeContext extends Serializable {
    SfNodeConfig getConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
