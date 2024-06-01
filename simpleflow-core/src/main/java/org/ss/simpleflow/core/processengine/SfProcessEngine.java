package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.processconfig.SfProcessConfig;

import java.util.Map;

public interface SfProcessEngine {

    String runProcess(SfProcessConfig processConfig,
                      String executionId,
                      Map<String, Object> params,
                      Map<String, Object> env);

    String runProcess(SfProcessConfig processConfig, Map<String, Object> params, Map<String, Object> env);

    String runProcess(SfProcessConfig processConfig, Map<String, Object> params);

}
