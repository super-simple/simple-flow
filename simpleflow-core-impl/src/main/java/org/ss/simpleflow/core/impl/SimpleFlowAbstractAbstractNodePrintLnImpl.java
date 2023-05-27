package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowNode;

public abstract class SimpleFlowAbstractAbstractNodePrintLnImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowNode {

    void scheduleNode() {
        try {
            runNode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
