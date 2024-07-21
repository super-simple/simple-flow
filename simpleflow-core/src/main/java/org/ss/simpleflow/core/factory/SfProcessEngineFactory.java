package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.aspect.SfEdgeAspect;
import org.ss.simpleflow.core.aspect.SfNodeAspect;
import org.ss.simpleflow.core.aspect.SfProcessAspect;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfComponentExecutionIdGenerator;
import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessExecutionIdGenerator;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;

public interface SfProcessEngineFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    SfProcessEngine<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
    createProcessEngine(SfProcessEngineConfig processEngineConfig,
                        SfControlLineFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> controlLineFactory,
                        SfDataLineFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> dataLineFactory,
                        SfEventFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> eventFactory,
                        SfNodeFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> nodeFactory,
                        SfEnumGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> enumGatewayFactory,
                        SfStreamIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> streamIteratorFactory,
                        SfGatewayFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> gatewayFactory,
                        SfAroundIteratorFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID> aroundIteratorFactory,
                        SfValidateManager<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> validateManager,
                        SfComponentExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> componentExecutionIdGenerator,
                        SfProcessExecutionIdGenerator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processExecutionIdGenerator,
                        SfContextFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> contextFactory,
                        List<SfEdgeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>> lineAspectList,
                        List<SfNodeAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, PROCESS_EXECUTION_ID>> nodeAspectList,
                        List<SfProcessAspect<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID>> processAspectList);

}
