package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;

import java.io.Serializable;

public interface SfLineContext<NODE_ID, LINE_ID, LINE_EXECUTION_ID,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>>
        extends SfVariableContext, Serializable {

    void setLineExecutionId(LINE_EXECUTION_ID lineExecutionId);

    LINE_EXECUTION_ID getLineExecutionId();

    void setLineConfig(LINE_CONFIG lineConfig);

    LINE_CONFIG getLineConfig();

}
