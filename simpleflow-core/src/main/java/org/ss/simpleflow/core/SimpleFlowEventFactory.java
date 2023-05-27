package org.ss.simpleflow.core;

public interface SimpleFlowEventFactory {
    SimpleFlowEvent getEvent(String processId, String lineId);
}
