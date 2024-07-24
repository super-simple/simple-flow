package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.MapUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.*;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeParameter;
import org.ss.simpleflow.core.node.SfNodeResult;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SfDefaultBasicValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
        PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
        NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> {

    private final SfDefaultNodeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigValidator;
    private final SfDefaultEdgeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> edgeConfigValidator;
    private final SfDefaultProcessConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigValidator;
    private final SfNodeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator;
    private final SfEdgeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> edgeConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator,
                                   SfEdgeConfigCustomValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> edgeConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.edgeConfigCustomValidator = edgeConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        edgeConfigValidator = new SfDefaultEdgeConfigValidator<>();
        processConfigValidator = new SfDefaultProcessConfigValidator<>();
    }

    public void preValidate(PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionGlobalContext,
                            SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.preValidate(processConfig, processContext, processEngineConfig);

        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<EDGE_CONFIG> edgeConfigList = processConfig.getEdgeConfigList();
        preValidateNodeAndEdge(nodeConfigList,
                               edgeConfigList,
                               processConfig,
                               null,
                               processContext,
                               executionGlobalContext,
                               processEngineConfig);

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                preValidateNodeAndEdge(processConfigGraph.getNodeConfigList(), processConfigGraph.getEdgeConfigList(),
                                       processConfig, processConfigGraph,
                                       processContext, executionGlobalContext, processEngineConfig);
            }
        }
    }

    private void preValidateNodeAndEdge(List<NODE_CONFIG> nodeConfigList,
                                        List<EDGE_CONFIG> edgeConfigList,
                                        PROCESS_CONFIG processConfig, PROCESS_CONFIG_GRAPH processConfigGraph,
                                        SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                        SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionGlobalContext,
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
            nodeConfigValidator.validate(nodeConfig, processConfig, processContext, processEngineConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.customValidate(nodeConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }

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
        }

        if (startNodeConfig == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_START_EVENT,
                                               processConfig,
                                               processConfigGraph,
                                               processContext,
                                               processEngineConfig);
        }

        Map<NODE_ID, NODE_CONFIG> nodeConfigMap = MapUtils.uniqueIndex(nodeConfigList,
                                                                       SfAbstractNodeConfig::getId);

        if (CollectionUtils.isNotEmpty(edgeConfigList)) {
            Set<EDGE_ID> edgeIdSet = new HashSet<>(edgeConfigList.size());
            Set<String> controlEdgeDistinctIdSet = new HashSet<>();
            Set<String> dataEdgeDistinctIdSet = new HashSet<>();
            for (EDGE_CONFIG edgeConfig : edgeConfigList) {
                edgeConfigValidator.validate(edgeConfig, processConfig, processContext, processEngineConfig);
                if (edgeConfigCustomValidator != null) {
                    edgeConfigCustomValidator.customValidate(edgeConfig,
                                                             processConfig,
                                                             processContext,
                                                             processEngineConfig);
                }

                EDGE_ID edgeId = edgeConfig.getId();
                if (edgeIdSet.contains(edgeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ID_REPEAT,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                edgeIdSet.add(edgeId);

                NODE_ID fromNodeId = edgeConfig.getFromNodeId();
                if (!nodeIdSet.contains(fromNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_NODE_ID,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                NODE_ID toNodeId = edgeConfig.getToNodeId();
                if (!nodeIdSet.contains(toNodeId)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_NODE_ID,
                                                    edgeConfig,
                                                    processConfig,
                                                    processConfigGraph,
                                                    processContext,
                                                    processEngineConfig);
                }
                if (edgeConfig.isControlEdge()) {
                    String uniqueKey = fromNodeId.toString() + toNodeId.toString();
                    if (controlEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.CONTROL_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    } else {
                        controlEdgeDistinctIdSet.add(uniqueKey);
                    }
                } else {
                    String fromResultKey = edgeConfig.getFromResultKey();
                    NODE_CONFIG fromNodeConfig = nodeConfigMap.get(fromNodeId);
                    Map<String, SfNodeResult> resultMap = fromNodeConfig.getResultMap();
                    boolean wrongFromResultKey = false;
                    if (MapUtils.isNullOrEmpty(resultMap)) {
                        wrongFromResultKey = true;
                    } else {
                        if (!resultMap.containsKey(fromResultKey)) {
                            wrongFromResultKey = true;
                        }
                    }
                    if (wrongFromResultKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_FROM_RESULT_KEY,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    }

                    String toParameterKey = edgeConfig.getToParameterKey();
                    NODE_CONFIG toNodeConfig = nodeConfigMap.get(toNodeId);
                    Map<String, SfNodeParameter> parameterMap = toNodeConfig.getParameterMap();
                    boolean wrongToParameterKey = false;
                    if (MapUtils.isNullOrEmpty(parameterMap)) {
                        wrongToParameterKey = true;
                    } else {
                        if (!parameterMap.containsKey(toParameterKey)) {
                            wrongToParameterKey = true;
                        }
                    }
                    if (wrongToParameterKey) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.WRONG_TO_PARAMETER_KEY,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    }

                    String uniqueKey = fromNodeId.toString() + fromResultKey +
                            toNodeId.toString() + toParameterKey;
                    if (dataEdgeDistinctIdSet.contains(uniqueKey)) {
                        throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.DATA_EDGE_REPEAT,
                                                        edgeConfig,
                                                        processConfig,
                                                        processConfigGraph,
                                                        processContext,
                                                        processEngineConfig);
                    } else {
                        dataEdgeDistinctIdSet.add(uniqueKey);
                    }
                }
            }
        }
    }

}
