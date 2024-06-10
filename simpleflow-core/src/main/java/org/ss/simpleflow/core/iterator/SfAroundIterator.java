package org.ss.simpleflow.core.iterator;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.Map;

public interface SfAroundIterator<NODE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends SfComponent {

    Map<String, Object> executeAroundIterator(Map<String, Object> params,
                                              SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> context) throws Exception;
}
