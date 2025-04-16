package org.ss.simpleflow.example;

import org.ss.simpleflow.core.context.*;
import org.ss.simpleflow.core.factory.SfExecutionContextFactory;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.context.*;

public class SimpleExecutionContextFactory
        implements SfExecutionContextFactory<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long> {
    @Override
    public SfNodeContext<Long, Long, Long, SimpleNodeConfig> createNodeContext() {
        return new AdaptNodeContext();
    }

    @Override
    public SfEdgeContext<Long, Long, Long, SimpleEdgeConfig> createEdgeContext() {
        return new AdaptEdgeContext();
    }

    @Override
    public SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long>
    createProcessContext() {
        return new AdaptProcessContext();
    }

    @Override
    public SfProcessExecutionContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long>
    createProcessExecutionContext() {
        return new AdaptProcessExecutionContext();
    }

    @Override
    public SfWholeExecutionContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long, Long>
    createWholeExecutionContext() {
        return new AdaptWholeExecutionContext();
    }

}
