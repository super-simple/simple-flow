package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowEventFactory {
    SimpleFlowAbstractEvent getEvent(String processId, String eventId, String eventCode, SimpleFlowNodeConfig eventConfig);
}
