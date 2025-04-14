package org.ss.simpleflow.example.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.event.SfEvent;
import org.ss.simpleflow.core.factory.SfEventFactory;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.node.EmptyEvent;

public class SimpleEventFactory implements
        SfEventFactory<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> {

    private EmptyEvent emptyEvent = new EmptyEvent();

    @Override
    public SfEvent<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> createEvent(
            SimpleNodeConfig simpleNodeConfig,
            SfNodeContext<Long, Long, Long, SimpleNodeConfig> nodeContext,
            SimpleProcessConfig simpleProcessConfig,
            SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {
        return emptyEvent;
    }

}
