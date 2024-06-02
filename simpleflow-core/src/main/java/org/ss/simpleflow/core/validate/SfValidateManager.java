package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public interface SfValidateManager {

    void manageValidate(SfProcessConfig processConfig, SfProcessEngineConfig processEngineConfig);

}
