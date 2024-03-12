package org.ss.simpleflow.core;

import org.ss.simpleflow.core.processconfig.SimpleFlowProcessConfig;

/**
 * 校验
 */
public interface SimpleFlowValidateAndPreprocess {

    SimpleFlowPreprocessData validateAndPreprocess(SimpleFlowProcessConfig processConfig);

}
