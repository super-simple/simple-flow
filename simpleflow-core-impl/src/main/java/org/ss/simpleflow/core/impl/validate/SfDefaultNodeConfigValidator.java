package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEventTypeConstant;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Set;

public class SfDefaultNodeConfigValidator
        <NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
                LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
                PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {
    public void validateSingleNodeConfig(NODE_CONFIG nodeConfig, PROCESS_CONFIG_GRAPH processConfigGraph) {
        NODE_ID id = nodeConfig.getId();
        if (id != null) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_ID, nodeConfig, processConfigGraph);
        }

        String nodeType = nodeConfig.getNodeType();
        if (StringUtils.isNullOrEmpty(nodeType)) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_TYPE, nodeConfig, processConfigGraph);
        }

        String nodeTypeUpperCase = nodeType.toUpperCase();
        switch (nodeTypeUpperCase) {
            case SfNodeTypeConstant.EVENT: {
                String eventCode = nodeConfig.getEventCode();
                if (StringUtils.isNullOrEmpty(eventCode)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_CODE,
                                                    nodeConfig,
                                                    processConfigGraph);
                }
                String eventType = nodeConfig.getEventType();
                if (SfEventTypeConstant.isLegal(eventType)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_TYPE,
                                                    nodeConfig,
                                                    processConfigGraph);
                }
                break;
            }
            case SfNodeTypeConstant.ENUM_GATEWAY: {
                Set<String> gatewayEnumSet = nodeConfig.getEnumGatewayEnumSet();
                if (CollectionUtils.isNullOrEmpty(gatewayEnumSet)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_GATEWAY_ENUM_SET,
                                                    nodeConfig,
                                                    processConfigGraph);
                }
                break;
            }
            case SfNodeTypeConstant.PROCESS: {
                PROCESS_CONFIG_ID processId = nodeConfig.getProcessId();
                if (processId != null) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_PROCESS_ID,
                                                    nodeConfig,
                                                    processConfigGraph);
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
                                                nodeConfig, processConfigGraph);
            }
        }
    }
}
