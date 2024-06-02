package org.ss.simpleflow.core.iterator;

import org.ss.simpleflow.core.component.SfComponent;
import org.ss.simpleflow.core.context.SfNodeContext;

import java.util.List;
import java.util.Map;

public interface SfStreamIterator extends SfComponent {

    List<Map<String, Object>> map(Map<String, Object> params,
                                  SfNodeContext context) throws Exception;

    default void collect(Map<String, Object> params, Map<String, Object> resultMap,
                         SfNodeContext context) throws Exception {
    }

}
