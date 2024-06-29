package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class SfAbstractNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> implements SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> {

    private NODE_EXECUTION_ID nodeExecutionId;

    private NODE_CONFIG nodeConfig;

    private Map<String, Object> variables;

    @Override
    public void setExecutionId(NODE_EXECUTION_ID nodeExecutionId) {
        this.nodeExecutionId = nodeExecutionId;
    }

    @Override
    public NODE_EXECUTION_ID getExecutionId() {
        return nodeExecutionId;
    }

    @Override
    public void setNodeConfig(NODE_CONFIG nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    @Override
    public NODE_CONFIG getNodeConfig() {
        return nodeConfig;
    }

    @Override
    public void setVariable(String key, Object value) {
        if (variables == null) {
            variables = new HashMap<>();
        }
        variables.put(key, value);
    }

    @Override
    public Object getVariable(String key) {
        if (variables == null) {
            return null;
        } else {
            return variables.get(key);
        }
    }
}
