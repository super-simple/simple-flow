package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowLine;

public class SimpleFlowTrueLineImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowLine {

    @Override
    public Boolean runLine() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-line:" + getId());
        return Boolean.TRUE;
    }
}
