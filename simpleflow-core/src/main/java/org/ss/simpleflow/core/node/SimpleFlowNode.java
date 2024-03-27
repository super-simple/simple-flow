package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SimpleFlowComponent;

import java.util.Map;

public interface SimpleFlowNode extends SimpleFlowComponent {

    Map<String, Object> runNode(Map<String, Object> params, SimpleFlowNodeContext context) throws Exception;
}
