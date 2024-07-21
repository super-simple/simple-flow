package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEventTypeConstant;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.Set;

public class SfDefaultNodeConfigValidator<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>, EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>, PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>, PROCESS_CONFIG extends SfProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>, PROCESS_EXECUTION_ID> {

    public void preValidate(NODE_CONFIG nodeConfig,
                            PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        NODE_ID id = nodeConfig.getId();
        if (id != null) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_ID,
                                            nodeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }
    }

    public void validate(NODE_CONFIG nodeConfig,
                         PROCESS_CONFIG processConfig,
                         SfProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                         SfProcessEngineConfig processEngineConfig) {
        String nodeType = nodeConfig.getNodeType();
        if (StringUtils.isNullOrEmpty(nodeType)) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_TYPE,
                                            nodeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        String nodeTypeUpperCase = nodeType.toUpperCase();
        switch (nodeTypeUpperCase) {
            case SfNodeTypeConstant.EVENT: {
                String eventCode = nodeConfig.getEventCode();
                if (StringUtils.isNullOrEmpty(eventCode)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_CODE,
                                                    nodeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                String eventType = nodeConfig.getEventType();
                if (SfEventTypeConstant.isLegal(eventType)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_TYPE,
                                                    nodeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                break;
            }
            case SfNodeTypeConstant.ENUM_GATEWAY: {
                Set<String> gatewayEnumSet = nodeConfig.getEnumGatewayEnumSet();
                if (CollectionUtils.isNullOrEmpty(gatewayEnumSet)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_GATEWAY_ENUM_SET,
                                                    nodeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                break;
            }
            case SfNodeTypeConstant.PROCESS: {
                PROCESS_CONFIG_ID processId = nodeConfig.getProcessId();
                if (processId != null) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_PROCESS_ID,
                                                    nodeConfig,
                                                    processConfig,
                                                    null,
                                                    processContext,
                                                    processEngineConfig);
                }
                break;
            }
            case SfNodeTypeConstant.NODE:
            case SfNodeTypeConstant.STREAM_ITERATOR:
            case SfNodeTypeConstant.AROUND_ITERATOR:
            case SfNodeTypeConstant.GATEWAY: {
                break;
            }
            default: {
                throw new SfNodeConfigException("unknown node type [" + nodeType + ']',
                                                SfNodeConfigExceptionCode.WRONG_NODE_TYPE,
                                                nodeConfig,
                                                processConfig,
                                                null,
                                                processContext,
                                                processEngineConfig);
            }
        }
    }
}
