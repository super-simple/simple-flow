package org.ss.simpleflow.example.node;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.event.SfEvent;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;

import java.util.Collections;
import java.util.Map;

public class EmptyEvent implements
        SfEvent<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> {

    @Override
    public Map<String, Object> executeEvent(Map<String, Object> params,
                                            SimpleNodeConfig simpleNodeConfig,
                                            SfNodeContext<Long, Long, Long, SimpleNodeConfig> nodeContext,
                                            SimpleProcessConfig simpleProcessConfig,
                                            SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) throws Exception {
        return Collections.emptyMap();
    }

}
