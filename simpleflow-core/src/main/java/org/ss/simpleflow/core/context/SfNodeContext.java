package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;

public interface SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends Serializable {

    void setExecutionId(NODE_EXECUTION_ID executionId);

    NODE_EXECUTION_ID getExecutionId();

    NODE_CONFIG getNodeConfig();

    void putVariable(String key, Object value);

    Object getVariable(String key);
}
