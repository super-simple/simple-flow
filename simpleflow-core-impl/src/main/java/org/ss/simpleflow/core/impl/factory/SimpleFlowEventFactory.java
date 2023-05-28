package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractEvent;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowEventFactory {
    SimpleFlowAbstractEvent getEvent(String processId, String eventId, String eventCode, SimpleFlowNodeConfig eventConfig);
}
