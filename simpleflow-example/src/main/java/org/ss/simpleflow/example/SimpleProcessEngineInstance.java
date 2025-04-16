package org.ss.simpleflow.example;

import org.ss.simpleflow.core.impl.processengine.SfDefaultProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.context.SimpleExecutionContextFactory;
import org.ss.simpleflow.example.factory.SimpleControlEdgeFactory;
import org.ss.simpleflow.example.factory.SimpleEventFactory;
import org.ss.simpleflow.example.factory.SimpleNodeFactory;

import java.util.Collections;

public class SimpleProcessEngineInstance {

    private final SfDefaultProcessEngine<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long> processEngine;

    public SimpleProcessEngineInstance() {
        SimpleControlEdgeFactory controlEdgeFactory = new SimpleControlEdgeFactory();
        SimpleEventFactory eventFactory = new SimpleEventFactory();
        SimpleNodeFactory nodeFactory = new SimpleNodeFactory();
        SimpleComponentExecutionIdGenerator componentExecutionIdGenerator = new SimpleComponentExecutionIdGenerator();
        SimpleProcessExecutionIdGenerator processExecutionIdGenerator = new SimpleProcessExecutionIdGenerator();
        SimpleExecutionContextFactory executionContextFactory = new SimpleExecutionContextFactory();
        processEngine = new SfDefaultProcessEngine<>(
                new SfProcessEngineConfig(),
                controlEdgeFactory,
                null,
                eventFactory,
                nodeFactory,
                null,
                null,
                null,
                null,
                processExecutionIdGenerator,
                componentExecutionIdGenerator,
                executionContextFactory,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public SfDefaultProcessEngine<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long> getProcessEngine() {
        return processEngine;
    }

}
