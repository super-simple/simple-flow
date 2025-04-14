package org.ss.simpleflow.example.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.factory.SfNodeFactory;
import org.ss.simpleflow.core.node.SfNode;
import org.ss.simpleflow.example.config.SimpleEdgeConfig;
import org.ss.simpleflow.example.config.SimpleNodeConfig;
import org.ss.simpleflow.example.config.SimpleProcessConfig;
import org.ss.simpleflow.example.node.EmptyNode;

public class SimpleNodeFactory implements
        SfNodeFactory<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> {

    private EmptyNode emptyNode = new EmptyNode();

    @Override
    public SfNode<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long, Long> createNode(
            SimpleNodeConfig simpleNodeConfig,
            SfNodeContext<Long, Long, Long, SimpleNodeConfig> nodeContext,
            SimpleProcessConfig simpleProcessConfig,
            SfProcessContext<Long, Long, Long, SimpleNodeConfig, SimpleEdgeConfig, SimpleProcessConfig, Long> processContext) {

        String nodeCode = simpleNodeConfig.getNodeCode();
        switch (nodeCode) {
            case "nodeEmpty": {
                return emptyNode;
            }
            default: {
                return null;
            }
        }
    }

}
