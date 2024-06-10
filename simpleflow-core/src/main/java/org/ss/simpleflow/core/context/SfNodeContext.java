package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;

public interface SfNodeContext<NODE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends Serializable {
    NODE_CONFIG getNodeConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
