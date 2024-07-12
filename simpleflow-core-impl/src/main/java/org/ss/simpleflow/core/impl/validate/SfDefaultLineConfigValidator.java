package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfLineTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigExceptionCode;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

public class SfDefaultLineConfigValidator
        <NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
                LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
                PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {
    public void validateSingleLineConfig(LINE_CONFIG lineConfig, PROCESS_CONFIG_GRAPH processConfigGraph) {
        LINE_ID id = lineConfig.getId();
        if (id != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_ID, lineConfig, processConfigGraph);
        }

        String lineType = lineConfig.getLineType();
        if (StringUtils.isNullOrEmpty(lineType)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_TYPE, lineConfig, processConfigGraph);
        }

        NODE_ID fromNodeId = lineConfig.getFromNodeId();
        if (fromNodeId != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_FROM_NODE_ID, lineConfig, processConfigGraph);
        }
        NODE_ID toNodeId = lineConfig.getToNodeId();
        if (toNodeId != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_TO_NODE_ID, lineConfig, processConfigGraph);
        }

        String lineTypeUpperCase = lineType.toUpperCase();
        switch (lineTypeUpperCase) {
            case (SfLineTypeConstant.CONTROL): {
                break;
            }
            case (SfLineTypeConstant.DATA): {
                String fromResultKey = lineConfig.getFromResultKey();
                if (StringUtils.isNullOrEmpty(fromResultKey)) {
                    throw new SfLineConfigException(SfLineConfigExceptionCode.NO_FROM_RESULT_KEY,
                                                    lineConfig,
                                                    processConfigGraph);
                }
                String toParameterKey = lineConfig.getToParameterKey();
                if (StringUtils.isNullOrEmpty(toParameterKey)) {
                    throw new SfLineConfigException(SfLineConfigExceptionCode.NO_TO_PARAMETER_KEY,
                                                    lineConfig,
                                                    processConfigGraph);
                }
                break;
            }
            default: {
                throw new SfLineConfigException("unknown line type [" + lineType + ']',
                                                SfLineConfigExceptionCode.WRONG_LINE_TYPE,
                                                lineConfig, processConfigGraph);
            }
        }
    }

}
