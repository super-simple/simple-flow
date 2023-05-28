package org.ss.simpleflow.core.impl.simple.event;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractEvent;
import org.ss.simpleflow.core.impl.SimpleFlowEventFactory;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public class SimpleFlowSimpleEventFactory implements SimpleFlowEventFactory {
    @Override
    public SimpleFlowAbstractEvent getEvent(String processId, String eventId, String eventCode, SimpleFlowNodeConfig eventConfig) {
        return new SimpleFlowSimpleEvent();
    }
}
