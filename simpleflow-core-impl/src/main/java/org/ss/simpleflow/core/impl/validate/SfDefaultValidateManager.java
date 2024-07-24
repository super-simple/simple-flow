package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.*;

import java.util.List;

public class SfDefaultValidateManager<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
        implements SfValidateManager<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    private final SfDefaultPreValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> preValidator;

    private final SfOrphanComponentCleaner<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> orphanComponentCleaner;

    SfDefaultValidateManager(SfNodeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator,
                             SfEdgeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> edgeConfigCustomValidator,
                             SfProcessConfigCustomValidate<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate) {
        preValidator = new SfDefaultPreValidator<>(nodeConfigCustomValidator,
                                                   edgeConfigCustomValidator,
                                                   processConfigCustomValidate);
        orphanComponentCleaner = new SfDefaultOrphanComponentCleaner<>();
    }

    @Override
    public void validate(PROCESS_CONFIG processConfig,
                         SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                                 NODE_CONFIG, EDGE_CONFIG,
                                 PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                         SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionGlobalContext,
                         SfProcessEngineConfig processEngineConfig) {

        preValidator.preValidate(processConfig, processContext, executionGlobalContext, processEngineConfig);

        if (processEngineConfig.isCleanOrphanNode()) {
            orphanComponentCleaner.cleanOrphanComponent(processConfig, executionGlobalContext);
        }

        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<EDGE_CONFIG> edgeConfigList = processConfig.getEdgeConfigList();

        validateProcess(nodeConfigList, edgeConfigList, processContext, processEngineConfig);

        validateSubProcessConfig(processConfig, processContext, processEngineConfig);

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();

        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                List<NODE_CONFIG> subProcessNodeConfigList = processConfigGraph.getNodeConfigList();
                List<EDGE_CONFIG> subProcessEdgeConfigList = processConfigGraph.getEdgeConfigList();
                validateProcess(subProcessNodeConfigList,
                                subProcessEdgeConfigList,
                                processContext,
                                processEngineConfig);
            }
        }
    }

    private void validateSubProcessConfig(PROCESS_CONFIG processConfig,
                                          SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                                                  NODE_CONFIG, EDGE_CONFIG,
                                                  PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                          SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
    }


    private void validateProcess(List<NODE_CONFIG> nodeConfigList,
                                 List<EDGE_CONFIG> edgeConfigList,
                                 SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                                         NODE_CONFIG, EDGE_CONFIG,
                                         PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {

        }
    }
}
