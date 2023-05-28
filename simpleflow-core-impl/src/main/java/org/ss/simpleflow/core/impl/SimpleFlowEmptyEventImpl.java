package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowEvent;

public class SimpleFlowEmptyEventImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowEvent {
    @Override
    public void runEvent() throws Exception {
        System.out.println(Thread.currentThread().getName() + "-event:" + getId());
    }

}
