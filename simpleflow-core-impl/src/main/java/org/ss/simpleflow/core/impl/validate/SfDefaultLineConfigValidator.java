package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfLineTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigExceptionCode;
import org.ss.simpleflow.core.line.SfLineConfig;

public class SfDefaultLineConfigValidator
        <NODE_ID, LINE_ID, LINE_CONFIG extends SfLineConfig<LINE_ID, NODE_ID>> {
    public void validateSingleLineConfig(LINE_CONFIG lineConfig) {
        LINE_ID id = lineConfig.getId();
        if (id != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_ID, lineConfig);
        }

        String lineType = lineConfig.getLineType();
        if (StringUtils.isNullOrEmpty(lineType)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_TYPE, lineConfig);
        }

        NODE_ID fromNodeId = lineConfig.getFromNodeId();
        if (fromNodeId != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_FROM_NODE_ID, lineConfig);
        }
        NODE_ID toNodeId = lineConfig.getToNodeId();
        if (toNodeId != null) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_TO_NODE_ID, lineConfig);
        }

        String lineTypeUpperCase = lineType.toUpperCase();
        switch (lineTypeUpperCase) {
            case (SfLineTypeConstant.CONTROL): {
                break;
            }
            case (SfLineTypeConstant.DATA): {
                String fromResultKey = lineConfig.getFromResultKey();
                if (StringUtils.isNullOrEmpty(fromResultKey)) {
                    throw new SfLineConfigException(SfLineConfigExceptionCode.NO_FROM_RESULT_KEY, lineConfig);
                }
                String toParameterKey = lineConfig.getToParameterKey();
                if (StringUtils.isNullOrEmpty(toParameterKey)) {
                    throw new SfLineConfigException(SfLineConfigExceptionCode.NO_TO_PARAMETER_KEY, lineConfig);
                }
                break;
            }
            default: {
                throw new SfLineConfigException("unknown line type [" + lineType + ']',
                                                SfLineConfigExceptionCode.WRONG_LINE_TYPE,
                                                lineConfig);
            }
        }
    }

}
