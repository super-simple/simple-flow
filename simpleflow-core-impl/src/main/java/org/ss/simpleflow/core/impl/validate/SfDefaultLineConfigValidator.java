package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.constant.SfLineTypeConstant;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigExceptionCode;
import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigValidator;

public class SfDefaultLineConfigValidator implements SfLineConfigValidator {
    @Override
    public void validateSingleLineConfig(SfLineConfig lineConfig) {
        String id = lineConfig.getId();
        if (StringUtils.isNullOrEmpty(id)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_ID, lineConfig);
        }

        String lineType = lineConfig.getLineType();
        if (StringUtils.isNullOrEmpty(lineType)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_LINE_TYPE, lineConfig);
        }

        String fromNodeId = lineConfig.getFromNodeId();
        if (StringUtils.isNullOrEmpty(fromNodeId)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_FROM_NODE_ID, lineConfig);
        }
        String toNodeId = lineConfig.getToNodeId();
        if (StringUtils.isNullOrEmpty(toNodeId)) {
            throw new SfLineConfigException(SfLineConfigExceptionCode.NO_TO_NODE_ID, lineConfig);
        }

        String lineTypeUpperCase = lineType.toUpperCase();
        switch (lineTypeUpperCase) {
            case (SfLineTypeConstant.RUN): {
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
