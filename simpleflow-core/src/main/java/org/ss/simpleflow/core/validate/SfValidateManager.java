package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;

public interface SfValidateManager {

    void basicValidate(SfProcessConfig processConfig);

    void graphValidate(SfProcessConfig processConfig);

    void businessValidate(SfProcessConfig processConfig);

}
