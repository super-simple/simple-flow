package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfNodeContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.gateway.SfEnumGateway;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public interface SfEnumGatewayFactory<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfEnumGateway<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
            LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>
    createEnumGateway(SfNodeContext<NODE_ID, PROCESS_CONFIG_ID, NODE_EXECUTION_ID, NODE_CONFIG> nodeContext,
                      SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG,
                              LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext);
}
