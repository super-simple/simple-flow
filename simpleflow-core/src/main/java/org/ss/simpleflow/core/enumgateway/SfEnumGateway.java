package org.ss.simpleflow.core.enumgateway;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.node.SfNodeContext;

import java.util.Map;

public interface SfEnumGateway extends SfComponent {

    String runGateway(Map<String, Object> params,
                      SfNodeContext context) throws Exception;
}
