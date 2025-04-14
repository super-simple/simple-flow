package org.ss.simpleflow.example.node;

import org.ss.simpleflow.core.context.SfEdgeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfControlEdge;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;

import java.util.Map;

public class EmptyControlEdge implements
        SfControlEdge<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> {

    @Override
    public boolean executeControlEdge(Map<String, Object> params,
                                      SimpleEdgeConfig simpleEdgeConfig,
                                      SfEdgeContext<Long, Long, Long, SimpleEdgeConfig> edgeContext,
                                      SimpleProcessConfig simpleProcessConfig,
                                      SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) throws Exception {
        System.out.println(edgeContext.getEdgeExecutionId());
        return true;
    }

}
