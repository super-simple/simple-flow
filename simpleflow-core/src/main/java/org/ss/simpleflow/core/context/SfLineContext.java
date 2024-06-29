package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;

import java.io.Serializable;

public interface SfLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends Serializable {

    void setExecutionId(LINE_EXECUTION_ID executionId);

    LINE_EXECUTION_ID getExecutionId();

    void setLineConfig(LINE_CONFIG lineConfig);

    LINE_CONFIG getLineConfig();

    void setVariable(String key, Object value);

    Object getVariable(String key);
}
