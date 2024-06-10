package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEventTypeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.Set;

public class SfDefaultNodeConfigValidator
        <NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>> {
    public void validateSingleNodeConfig(NODE_CONFIG nodeConfig) {
        NODE_ID id = nodeConfig.getId();
        if (id != null) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_ID, nodeConfig);
        }

        String nodeType = nodeConfig.getNodeType();
        if (StringUtils.isNullOrEmpty(nodeType)) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_TYPE, nodeConfig);
        }

        String nodeTypeUpperCase = nodeType.toUpperCase();
        switch (nodeTypeUpperCase) {
            case SimpleFlowNodeTypeConstant.EVENT: {
                String eventCode = nodeConfig.getEventCode();
                if (StringUtils.isNullOrEmpty(eventCode)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_CODE, nodeConfig);
                }
                String eventType = nodeConfig.getEventType();
                if (SfEventTypeConstant.isLegal(eventType)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_TYPE, nodeConfig);
                }
                break;
            }
            case SimpleFlowNodeTypeConstant.ENUM_GATEWAY: {
                Set<String> gatewayEnumSet = nodeConfig.getEnumGatewayEnumSet();
                if (CollectionUtils.isNullOrEmpty(gatewayEnumSet)) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_GATEWAY_ENUM_SET, nodeConfig);
                }
                break;
            }
            case SimpleFlowNodeTypeConstant.PROCESS: {
                PROCESS_CONFIG_ID processId = nodeConfig.getProcessId();
                if (processId != null) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_PROCESS_ID, nodeConfig);
                }
                break;
            }
            case SimpleFlowNodeTypeConstant.NODE:
            case SimpleFlowNodeTypeConstant.STREAM_ITERATOR:
            case SimpleFlowNodeTypeConstant.AROUND_ITERATOR:
            case SimpleFlowNodeTypeConstant.GATEWAY: {
                break;
            }
            default: {
                throw new SfNodeConfigException("unknown node type [" + nodeType + ']',
                                                SfNodeConfigExceptionCode.WRONG_NODE_TYPE,
                                                nodeConfig);
            }
        }
    }
}
