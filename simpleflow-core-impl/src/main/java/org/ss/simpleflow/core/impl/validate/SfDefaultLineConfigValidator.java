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

        String lineTypeUpperCase = lineType.toUpperCase();
        switch (lineTypeUpperCase) {
            case (SfLineTypeConstant.RUN): {

            }
            case (SfLineTypeConstant.DATA): {

            }
            default: {
                throw new SfLineConfigException("unknown line type [" + lineType + ']', SfLineConfigExceptionCode.WRONG_LINE_TYPE, lineConfig);
            }
        }
    }

}
