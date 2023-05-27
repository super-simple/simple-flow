package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowNode;

public class SimpleFlowAbstractNodePrintLnImpl extends SimpleFlowAbstractAbstractNodePrintLnImpl implements SimpleFlowNode {
    @Override
    public void runNode() throws Exception {
        System.out.println("node:" + getId());
    }

}
