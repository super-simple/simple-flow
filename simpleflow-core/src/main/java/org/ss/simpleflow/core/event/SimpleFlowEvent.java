package org.ss.simpleflow.core.event;

import org.ss.simpleflow.core.component.SimpleFlowComponent;

public interface SimpleFlowEvent extends SimpleFlowComponent {
    void runEvent() throws Exception;
}
