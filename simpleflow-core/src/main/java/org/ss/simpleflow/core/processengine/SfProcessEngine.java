package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;

public interface SfProcessEngine {

    String runProcess(SfProcessConfig processConfig);

}
