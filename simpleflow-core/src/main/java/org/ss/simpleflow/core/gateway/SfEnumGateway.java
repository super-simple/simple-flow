package org.ss.simpleflow.core.gateway;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.Map;

public interface SfEnumGateway<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends SfComponent {

    String executeEnumGateway(Map<String, Object> params,
                              SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> context) throws Exception;
}
