package org.ss.simpleflow.core.event;

import org.ss.simpleflow.core.component.SimpleFlowComponent;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

public interface SimpleFlowEvent extends SimpleFlowComponent {

    SimpleFlowNodeConfig getConfig();

    void runEvent() throws Exception;
}
