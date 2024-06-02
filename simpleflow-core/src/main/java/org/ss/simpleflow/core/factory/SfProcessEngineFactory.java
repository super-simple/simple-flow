package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.processengine.SfProcessEngine;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfValidateManager;

public interface SfProcessEngineFactory {

    SfProcessEngine createProcessEngine(SfProcessEngineConfig processEngineConfig,
                                        SfControlLineFactory controlLineFactory,
                                        SfDataLineFactory dataLineFactory,
                                        SfEventFactory eventFactory,
                                        SfNodeFactory nodeFactory,
                                        SfEnumGatewayFactory enumGatewayFactory,
                                        SfStreamIteratorFactory streamIteratorFactory,
                                        SfGatewayFactory gatewayFactory,
                                        SfAroundIteratorFactory aroundIteratorFactory,
                                        SfValidateManager validateManager);

}
