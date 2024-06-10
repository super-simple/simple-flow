package org.ss.simpleflow.core.iterator;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.List;
import java.util.Map;

public interface SfStreamIterator<NODE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> extends SfComponent {

    List<Map<String, Object>> map(Map<String, Object> params,
                                  SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> context) throws Exception;

    default void collect(Map<String, Object> params, Map<String, Object> resultMap,
                         SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> context) throws Exception {
    }

}
