package org.ss.simpleflow.example.config;

import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public class SimpleProcessConfig
        extends SfAbstractProcessConfig<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig> {
    @Override
    public SimpleNodeConfig[] createNodeConfigArray(int length) {
        return new SimpleNodeConfig[length];
    }

    @Override
    public SimpleEdgeConfig[] createEdgeConfigArray(int length) {
        return new SimpleEdgeConfig[length];
    }
}
