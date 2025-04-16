package org.ss.simpleflow.example;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleProcessExecutionIdGenerator
        implements SfProcessExecutionIdGenerator<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public Long generateProcessExecutionId(SimpleProcessConfig simpleProcessConfig,
                                           SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {
        return atomicLong.incrementAndGet();
    }

}
