package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfEdgeConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;

public class SfDefaultEdgeConfigValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    public void basicValidate(EC edgeConfig,
                              PC processConfig,
                              SfProcessPreprocessConfig processPreprocessConfig) {
        EI edgeId = edgeConfig.getId();
        if (edgeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EI,
                                            edgeConfig,
                                            processConfig,
                                            processPreprocessConfig);
        }

        String edgeType = edgeConfig.getEdgeType();
        if (StringUtils.isNullOrEmpty(edgeType)) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_EDGE_TYPE,
                                            edgeConfig,
                                            processConfig,
                                            processPreprocessConfig);
        }

        NI fromNodeId = edgeConfig.getFromNodeId();
        if (fromNodeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_FROM_NI,
                                            edgeConfig,
                                            processConfig,
                                            processPreprocessConfig);
        }
        NI toNodeId = edgeConfig.getToNodeId();
        if (toNodeId == null) {
            throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.NO_TO_NI,
                                            edgeConfig,
                                            processConfig,
                                            processPreprocessConfig);
        }

        String edgeTypeUpperCase = edgeType.toUpperCase();
        switch (edgeTypeUpperCase) {
            case (SfEdgeTypeConstant.CONTROL): {
                break;
            }
            case (SfEdgeTypeConstant.DATA): {
                int fromResultIndex = edgeConfig.getFromResultIndex();
                if (fromResultIndex < 0) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ILLEGAL_RESULT_INDEX,
                                                    edgeConfig,
                                                    processConfig,
                                                    processPreprocessConfig);
                }
                int toParameterIndex = edgeConfig.getToParameterIndex();
                if (toParameterIndex < 0) {
                    throw new SfEdgeConfigException(SfEdgeConfigExceptionCode.ILLEGAL_PARAMETER_INDEX,
                                                    edgeConfig,
                                                    processConfig,
                                                    processPreprocessConfig);
                }
                break;
            }
            default: {
                throw new SfEdgeConfigException("unknown edge type [" + edgeType + ']',
                                                SfEdgeConfigExceptionCode.WRONG_EDGE_TYPE,
                                                edgeConfig,
                                                processConfig,
                                                processPreprocessConfig);
            }
        }
    }

}
