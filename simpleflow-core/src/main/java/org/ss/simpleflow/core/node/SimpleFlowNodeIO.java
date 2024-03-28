package org.ss.simpleflow.core.node;

public interface SimpleFlowNodeIO {
    default boolean isNotNull() {
        return true;
    }

    String getOwnership();
}
