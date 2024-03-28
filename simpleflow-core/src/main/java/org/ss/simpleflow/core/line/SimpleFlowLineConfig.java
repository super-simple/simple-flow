package org.ss.simpleflow.core.line;

public interface SimpleFlowLineConfig {

    String getId();

    String getFromNodeId();

    String getToNodeId();

    String getLineType();

    String getFromParameterName();

    String getToParameterName();
}
