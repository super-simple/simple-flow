package org.ss.simpleflow.core.line;

public interface SfLineConfig {

    String getId();

    String getFromNodeId();

    String getToNodeId();

    String getLineType();

    boolean isDefaultLine();

    String getFromParameterName();

    String getToParameterName();
}