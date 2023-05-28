package org.ss.simpleflow.core.impl.simple.line;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractLine;
import org.ss.simpleflow.core.line.SimpleFlowLine;

public class SimpleFlowSimpleLine extends SimpleFlowAbstractLine implements SimpleFlowLine {

    @Override
    public Boolean runLine() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-line:" + getId());
        return Boolean.TRUE;
    }
}
