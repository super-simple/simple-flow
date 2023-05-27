package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowEvent;

public class SimpleFlowAbstractEmptyEventImpl extends SimpleFlowAbstractComponentImpl implements SimpleFlowEvent {
    @Override
    public void runEvent() throws Exception {
        System.out.println("event:" + getId());
    }


    protected final String aaa(){
        return null;
    }
}
