package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public class SfDefaultEdgeConfigValidator<NI, EI, PCI, NC extends SfAbstractNodeConfig<NI, PCI>, EC extends SfAbstractEdgeConfig<EI, NI>, PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>, PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>, PEI> {

    public void basicValidate(EC edgeConfig,
                              PC processConfig,
                              SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                              SfProcessEngineConfig processEngineConfig) {
        EI edgeId = edgeConfig.getId();
        if (edgeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EI,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        String edgeType = edgeConfig.getEdgeType();
        if (StringUtils.isNullOrEmpty(edgeType)) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EDGE_TYPE,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        NI fromNodeId = edgeConfig.getFromNodeId();
        if (fromNodeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_FROM_NI,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }
        NI toNodeId = edgeConfig.getToNodeId();
        if (toNodeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_TO_NI,
                                            edgeConfig,
                                            processConfig,
                                            null,
                                            processContext,
                                            processEngineConfig);
        }

        String edgeTypeUpperCase = edgeType.toUpperCase();
        switch (edgeTypeUpperCase) {
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
                throw new SfEdgeConfigException("unknown edge type [" + edgeType + ']',
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
