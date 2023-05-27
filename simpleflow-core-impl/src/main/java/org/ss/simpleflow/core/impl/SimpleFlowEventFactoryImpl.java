package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowEvent;
import org.ss.simpleflow.core.SimpleFlowEventFactory;

public class SimpleFlowEventFactoryImpl implements SimpleFlowEventFactory {
    @Override
    public SimpleFlowEvent getEvent(String processId, String lineId) {
        return new SimpleFlowAbstractEmptyEventImpl();
    }
}
