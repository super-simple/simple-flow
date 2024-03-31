package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SimpleFlowEventTypeConstant;
import org.ss.simpleflow.core.constant.SimpleFlowNodeTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowNodeConfigExceptionCode;
import org.ss.simpleflow.core.node.SimpleFlowNodeConfig;
import org.ss.simpleflow.core.validate.SimpleFlowNodeConfigValidator;

import java.util.Set;

public class SimpleFlowDefaultNodeConfigValidator implements SimpleFlowNodeConfigValidator {
    @Override
    public void validateSingleNodeConfig(SimpleFlowNodeConfig nodeConfig) {
        String id = nodeConfig.getId();
        if (id == null) {
            throw new SimpleFlowNodeConfigException(SimpleFlowNodeConfigExceptionCode.NO_NODE_ID, nodeConfig);
        }

        String nodeType = nodeConfig.getNodeType().toUpperCase();
        switch (nodeType) {
            case SimpleFlowNodeTypeConstant.EVENT: {
                String eventCode = nodeConfig.getEventCode();
                if (StringUtils.isNullOrEmpty(eventCode)) {
                    throw new SimpleFlowNodeConfigException(SimpleFlowNodeConfigExceptionCode.NO_EVENT_CODE, nodeConfig);
                }
                String eventType = nodeConfig.getEventType();
                if (SimpleFlowEventTypeConstant.isLegal(eventType)) {
                    throw new SimpleFlowNodeConfigException(SimpleFlowNodeConfigExceptionCode.NO_EVENT_TYPE, nodeConfig);
                }
            }
            case SimpleFlowNodeTypeConstant.ENUM_GATEWAY: {
                Set<String> gatewayEnumSet = nodeConfig.getGatewayEnumSet();
                if (CollectionUtils.isNullOrEmpty(gatewayEnumSet)) {
                    throw new SimpleFlowNodeConfigException(SimpleFlowNodeConfigExceptionCode.NO_GATEWAY_ENUM_SET, nodeConfig);
                }
            }
        }
    }
}
