package org.ss.simpleflow.core.event;

import org.ss.simpleflow.core.component.SimpleFlowComponent;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;

import java.util.Map;

public interface SimpleFlowEvent extends SimpleFlowComponent {

    Map<String, Object> runEvent(Map<String, Object> params,
                                 SimpleFlowNodeConfig eventConfig) throws Exception;
}
