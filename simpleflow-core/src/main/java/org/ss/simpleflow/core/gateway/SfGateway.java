package org.ss.simpleflow.core.gateway;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.Map;

public interface SfGateway extends SfComponent {

    Map<String, Object> executeGateway(Map<String, Object> params,
                                       SfNodeContext context) throws Exception;
}
