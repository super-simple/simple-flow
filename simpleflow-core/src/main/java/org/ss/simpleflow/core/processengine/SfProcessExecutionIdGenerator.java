package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;

public interface SfProcessExecutionIdGenerator {

    String generateProcessExecutionId(SfProcessConfig processConfig);
}
