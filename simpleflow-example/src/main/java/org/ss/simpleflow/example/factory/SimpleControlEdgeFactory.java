package org.ss.simpleflow.example.factory;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfControlEdge;
import org.ss.simpleflow.core.factory.SfControlEdgeFactory;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.node.EmptyControlEdge;

public class SimpleControlEdgeFactory implements
        SfControlEdgeFactory<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> {

    private EmptyControlEdge emptyControlEdge = new EmptyControlEdge();

    @Override
    public SfControlEdge<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> createControlEdge(
            SimpleEdgeConfig simpleEdgeConfig,
            SfEdgeContext<Long, Long, Long, SimpleEdgeConfig> edgeContext,
            SimpleProcessConfig simpleProcessConfig,
            SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {

        String edgeCode = simpleEdgeConfig.getEdgeCode();
        switch (edgeCode) {
            case "edgeEmpty": {
                return emptyControlEdge;
            }
            default: {
                return null;
            }
        }
    }

}
