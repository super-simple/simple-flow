package org.ss.simpleflow.core.node;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.Map;

public interface SfNode<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends SfComponent {

    Map<String, Object> executeNode(Map<String, Object> params,
                                    SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> context) throws Exception;
}
