package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfNodeTypeConstant;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.Set;

public class SfDefaultNodeConfigValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    public void basicValidate(NC nodeConfig,
                              PC processConfig,
                              SfProcessEngineConfig processEngineConfig) {
        NI nodeId = nodeConfig.getId();
        if (nodeId == null) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_ID,
                                            nodeConfig,
                                            processConfig,
                                            processEngineConfig);
        }

        String nodeType = nodeConfig.getNodeType();
        if (StringUtils.isNullOrEmpty(nodeType)) {
            throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_NODE_TYPE,
                                            nodeConfig,
                                            processConfig,
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
                                                    processEngineConfig);
                }
                if (nodeConfig.isLegalEventType()) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_EVENT_TYPE,
                                                    nodeConfig,
                                                    processConfig,
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
                                                    processEngineConfig);
                }
                break;
            }
            case SfNodeTypeConstant.PROCESS: {
                PCI processId = nodeConfig.getProcessId();
                if (processId == null) {
                    throw new SfNodeConfigException(SfNodeConfigExceptionCode.NO_PROCESS_ID,
                                                    nodeConfig,
                                                    processConfig,
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
                                                processEngineConfig);
            }
        }
    }
}
