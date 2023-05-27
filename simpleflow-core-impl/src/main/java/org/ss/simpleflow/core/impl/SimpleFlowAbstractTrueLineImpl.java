package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowLine;

public class SimpleFlowAbstractTrueLineImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowLine {

    @Override
    public Boolean runLine() throws Exception {
        System.out.println("line:" + getId());
        return Boolean.TRUE;
    }
}
