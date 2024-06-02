package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfSubProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.ArrayList;
import java.util.List;

public class SfDefaultValidateManager implements SfValidateManager {

    private final SfDefaultBasicValidator basicValidator;

    public SfDefaultValidateManager(SfNodeConfigCustomValidator nodeConfigCustomValidator,
                                    SfLineConfigCustomValidator lineConfigCustomValidator) {
        basicValidator = new SfDefaultBasicValidator(nodeConfigCustomValidator, lineConfigCustomValidator);
    }


    @Override
    public void manageValidate(SfProcessConfig processConfig, SfProcessEngineConfig processEngineConfig) {
        List<SfSubProcessConfig> subProcessConfigList = processConfig.getSubProcessConfigList();
        int processConfigListSize = 1;
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            processConfigListSize += subProcessConfigList.size();
        }
        List<Object> objects = new ArrayList<>(processConfigListSize);
        basicValidator.basicValidate(processConfig);


    }

}
