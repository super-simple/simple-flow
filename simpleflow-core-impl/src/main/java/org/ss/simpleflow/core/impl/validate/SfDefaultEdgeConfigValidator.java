package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfDefaultEdgeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>, EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>, PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>, PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>, PROCESS_EXECUTION_ID> {

    public void preValidate(EDGE_CONFIG edgeConfig,
                            PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        EDGE_ID id = edgeConfig.getId();
        if (id != null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EDGE_ID,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }
    }

    public void validate(EDGE_CONFIG edgeConfig,
                         PROCESS_CONFIG processConfig,
                         SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                         SfProcessEngineConfig processEngineConfig) {
        String lineType = edgeConfig.getEdgeType();
        if (StringUtils.isNullOrEmpty(lineType)) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EDGE_TYPE,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        NODE_ID fromNodeId = edgeConfig.getFromNodeId();
        if (fromNodeId != null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_FROM_NODE_ID,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }
        NODE_ID toNodeId = edgeConfig.getToNodeId();
        if (toNodeId != null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_TO_NODE_ID,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        String lineTypeUpperCase = lineType.toUpperCase();
        switch (lineTypeUpperCase) {
            case (SfEdgeTypeConstant.CONTROL): {
                break;
            }
            case (SfEdgeTypeConstant.DATA): {
                String fromResultKey = edgeConfig.getFromResultKey();
                if (StringUtils.isNullOrEmpty(fromResultKey)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_FROM_RESULT_KEY,
                                                    edgeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                String toParameterKey = edgeConfig.getToParameterKey();
                if (StringUtils.isNullOrEmpty(toParameterKey)) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_TO_PARAMETER_KEY,
                                                    edgeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                break;
            }
            default: {
                throw new SfEdgeConfigException("unknown edge type [" + lineType + ']',
                                                SfEdgeConfigExceptionCode.WRONG_EDGE_TYPE,
                                                edgeConfig,
                                                processConfig,
                                                null,
                                                processContext,
                                                processEngineConfig);
            }
        }
    }

}
