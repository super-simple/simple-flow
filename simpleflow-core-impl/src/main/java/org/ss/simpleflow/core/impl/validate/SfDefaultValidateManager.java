package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManager implements SfValidateManager {

    private final SfDefaultBasicValidator basicValidator;

    SfDefaultValidateManager(SfNodeConfigCustomValidator nodeConfigCustomValidator,
                             SfLineConfigCustomValidator lineConfigCustomValidator) {
        basicValidator = new SfDefaultBasicValidator(nodeConfigCustomValidator, lineConfigCustomValidator);
    }


    @Override
    public void manageValidate(SfProcessConfig processConfig, SfProcessEngineConfig processEngineConfig) {

    }

}
