package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SfComponentConfig;

public interface SfLineConfig<LINE_ID, NODE_ID> extends SfComponentConfig {

    LINE_ID getId();

    NODE_ID getFromNodeId();

    NODE_ID getToNodeId();

    String getLineType();

    String getFromResultKey();

    String getToParameterKey();

    SfLineIndexEntry getLineIndexEntry();
}
