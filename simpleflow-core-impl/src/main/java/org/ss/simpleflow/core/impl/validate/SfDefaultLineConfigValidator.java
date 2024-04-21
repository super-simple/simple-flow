package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
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


    }

}
