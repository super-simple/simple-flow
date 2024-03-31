package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.StringUtils;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SimpleFlowLineConfigExceptionCode;
import org.ss.simpleflow.core.line.SimpleFlowLineConfig;
import org.ss.simpleflow.core.validate.SimpleFlowLineConfigValidator;

public class SimpleFlowDefaultLineConfigValidator implements SimpleFlowLineConfigValidator {
    @Override
    public void validateSingleLineConfig(SimpleFlowLineConfig lineConfig) {
        String id = lineConfig.getId();
        if (StringUtils.isNullOrEmpty(id)) {
            throw new SimpleFlowLineConfigException(SimpleFlowLineConfigExceptionCode.NO_LINE_ID, lineConfig);
        }


    }

}
