package org.ss.simpleflow.example;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleComponentExecutionIdGenerator
        implements SfComponentExecutionIdGenerator<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long> {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public Long generateNodeExecutionId(SimpleEdgeConfig simpleEdgeConfig,
                                        SfNodeContext<Long, Long, Long, SimpleNodeConfig> nodeContext,
                                        SimpleProcessConfig simpleProcessConfig,
                                        SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {
        return atomicLong.incrementAndGet();
    }

    @Override
    public Long generateEdgeExecutionId(SimpleEdgeConfig simpleEdgeConfig,
                                        SfEdgeContext<Long, Long, Long, SimpleEdgeConfig> edgeContext,
                                        SimpleProcessConfig simpleProcessConfig,
                                        SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {
        return atomicLong.incrementAndGet();
    }
}
