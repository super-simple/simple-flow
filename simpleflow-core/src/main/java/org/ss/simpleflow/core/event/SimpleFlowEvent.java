package org.ss.simpleflow.core.event;

import org.ss.simpleflow.core.component.SimpleFlowComponent;
import org.ss.simpleflow.core.node.SimpleFlowNodeContext;

import java.util.Map;

public interface SimpleFlowEvent extends SimpleFlowComponent {

    Map<String, Object> runEvent(Map<String, Object> params,
                                 SimpleFlowNodeContext context) throws Exception;
}
