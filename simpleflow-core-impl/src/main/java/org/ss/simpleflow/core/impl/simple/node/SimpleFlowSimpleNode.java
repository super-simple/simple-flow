package org.ss.simpleflow.core.impl.simple.node;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractNode;
import org.ss.simpleflow.core.node.SimpleFlowNode;

public class SimpleFlowSimpleNode extends SimpleFlowAbstractNode implements SimpleFlowNode {
    @Override
    public void runNode() throws Exception {
        System.out.println("type:node" + ",Thread:" + Thread.currentThread().getName() + ",id:" + getId());
    }

}
