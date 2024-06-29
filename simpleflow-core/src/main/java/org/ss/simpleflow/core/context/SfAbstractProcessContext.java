package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.HashMap;
import java.util.Map;

public abstract class SfAbstractProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID>
        implements SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
        LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> {

    private PROCESS_EXECUTION_ID processExecutionId;

    private PROCESS_CONFIG processConfig;

    private Map<String, Object> variables;

    @Override
    public void setExecutionId(PROCESS_EXECUTION_ID processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    @Override
    public PROCESS_EXECUTION_ID getExecutionId() {
        return processExecutionId;
    }

    @Override
    public void setProcessConfig(PROCESS_CONFIG processConfig) {
        this.processConfig = processConfig;
    }

    @Override
    public PROCESS_CONFIG getProcessConfig() {
        return processConfig;
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
