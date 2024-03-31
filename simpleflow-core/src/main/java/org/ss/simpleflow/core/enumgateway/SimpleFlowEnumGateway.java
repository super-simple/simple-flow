package org.ss.simpleflow.core.enumgateway;

import org.ss.simpleflow.core.component.SimpleFlowComponent;
import org.ss.simpleflow.core.node.SimpleFlowNodeContext;

import java.util.Map;

public interface SimpleFlowEnumGateway extends SimpleFlowComponent {

    String runGateway(Map<String, Object> params,
                      SimpleFlowNodeContext context) throws Exception;
}
