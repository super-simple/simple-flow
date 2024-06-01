package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;

/**
 * 校验
 */
public interface SfProcessConfigCustomValidate {

    void validateProcess(SfProcessConfig processConfig);

}
