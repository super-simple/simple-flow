package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultBasicValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID> {

    private final SfDefaultNodeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigValidator;
    private final SfDefaultEdgeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigValidator;
    private final SfDefaultProcessConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigValidator;
    private final SfNodeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator;
    private final SfLineConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator,
                                   SfLineConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.lineConfigCustomValidator = lineConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        lineConfigValidator = new SfDefaultEdgeConfigValidator<>();
        processConfigValidator = new SfDefaultProcessConfigValidator<>();
    }

    public void preValidate(PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.preValidate(processConfig, processContext, processEngineConfig);

        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<EDGE_CONFIG> lineConfigList = processConfig.getLineConfigList();
        preValidateNodeAndLine(nodeConfigList,
                               lineConfigList,
                               processConfig,
                               null,
                               processContext,
                               processEngineConfig);

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                preValidateNodeAndLine(processConfigGraph.getNodeConfigList(), processConfigGraph.getLineConfigList(),
                                       processConfig, processConfigGraph,
                                       processContext, processEngineConfig);
            }
        }
    }

    private void preValidateNodeAndLine(List<NODE_CONFIG> nodeConfigList, List<EDGE_CONFIG> lineConfigList,
                                        PROCESS_CONFIG processConfig, PROCESS_CONFIG_GRAPH processConfigGraph,
                                        SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                        SfProcessEngineConfig processEngineConfig) {
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_NODE,
                                               processConfig,
                                               processConfigGraph,
                                               processContext,
                                               processEngineConfig);
        }

        Set<NODE_ID> nodeIdSet = new HashSet<>(nodeConfigList.size());

        NODE_CONFIG startNodeConfig = null;

        for (NODE_CONFIG nodeConfig : nodeConfigList) {
            NODE_ID nodeId = nodeConfig.getId();
            if (nodeIdSet.contains(nodeId)) {
                throw new SfNodeConfigException(SfNodeConfigExceptionCode.ID_REPEAT,
                                                nodeConfig,
                                                processConfig,
                                                processConfigGraph,
                                                processContext,
                                                processEngineConfig);
            }
            nodeIdSet.add(nodeId);

            boolean startNode = nodeConfig.isStartNode();
            if (startNode) {
                if (startNodeConfig == null) {
                    startNodeConfig = nodeConfig;
                } else {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.START_EVENT_REPEAT,
                                                    nodeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
            }

            nodeConfigValidator.preValidate(nodeConfig, processConfig, processContext, processEngineConfig);

        }

        if (startNodeConfig == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_START_EVENT,
                                               processConfig,
                                               processConfigGraph,
                                               processContext,
                                               processEngineConfig);
        }

        if (CollectionUtils.isNotEmpty(lineConfigList)) {
            Set<EDGE_ID> lineIdSet = new HashSet<>(lineConfigList.size());
            for (EDGE_CONFIG edgeConfig : lineConfigList) {
                EDGE_ID lineId = edgeConfig.getId();
                if (lineIdSet.contains(lineId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ID_REPEAT,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                lineIdSet.add(lineId);

                lineConfigValidator.preValidate(edgeConfig, processConfig, processContext, processEngineConfig);
            }
        }
    }

    public void basicValidate(PROCESS_CONFIG processConfig,
                              SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                              SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        for (NODE_CONFIG nodeConfig : nodeConfigList) {
            nodeConfigValidator.validate(nodeConfig, processConfig, processContext, processEngineConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.customValidate(nodeConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }
        }

        List<EDGE_CONFIG> lineConfigList = processConfig.getLineConfigList();
        for (EDGE_CONFIG edgeConfig : lineConfigList) {
            lineConfigValidator.validate(edgeConfig, processConfig, processContext, processEngineConfig);
            if (lineConfigCustomValidator != null) {
                lineConfigCustomValidator.customValidate(edgeConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }
        }
    }

}
