package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowNode;

public class SimpleFlowNodePrintlnImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowNode {
    @Override
    public void runNode() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-node:" + getId());
    }

}
