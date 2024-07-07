package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>>
        implements SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> {

    private final SfDefaultBasicValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> basicValidator;

    public SfDefaultValidateManager(SfNodeConfigCustomValidator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeConfigCustomValidator,
                                    SfLineConfigCustomValidator<NODE_ID, LINE_ID, LINE_CONFIG> lineConfigCustomValidator) {
        basicValidator = new SfDefaultBasicValidator<>(nodeConfigCustomValidator, lineConfigCustomValidator);
    }


    @Override
    public void manageValidate(PROCESS_CONFIG_GRAPH processConfig, SfProcessEngineConfig processEngineConfig) {

    }

}
