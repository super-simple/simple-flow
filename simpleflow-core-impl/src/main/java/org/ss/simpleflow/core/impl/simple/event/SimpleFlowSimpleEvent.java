package org.ss.simpleflow.core.impl.simple.event;

import org.ss.simpleflow.core.event.SimpleFlowEvent;
import org.ss.simpleflow.core.impl.SimpleFlowAbstractEvent;

public class SimpleFlowSimpleEvent extends SimpleFlowAbstractEvent implements SimpleFlowEvent {
    @Override
    public void runEvent() throws Exception {
        System.out.println("type:event" + ",Thread:" + Thread.currentThread().getName() + ",id:" + getId());
    }

}
