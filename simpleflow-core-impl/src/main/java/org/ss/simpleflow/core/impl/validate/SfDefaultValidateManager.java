package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;

public class SfDefaultValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>>
        implements SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG> {

    private final SfDefaultBasicValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> basicValidator;

    public SfDefaultValidateManager(SfNodeConfigCustomValidator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeConfigCustomValidator,
                                    SfLineConfigCustomValidator<NODE_ID, LINE_ID, LINE_CONFIG> lineConfigCustomValidator) {
        basicValidator = new SfDefaultBasicValidator<>(nodeConfigCustomValidator, lineConfigCustomValidator);
    }

    @Override
    public void validate(PROCESS_CONFIG processConfig, SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<LINE_CONFIG> lineConfigList = processConfig.getLineConfigList();


        validateProcess(nodeConfigList, lineConfigList, processEngineConfig);
        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();

        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                List<NODE_CONFIG> subProcessNodeConfigList = processConfigGraph.getNodeConfigList();
                List<LINE_CONFIG> subProcessLineConfigList = processConfigGraph.getLineConfigList();
                validateProcess(subProcessNodeConfigList, subProcessLineConfigList, processEngineConfig);
            }
        }
    }

    private void validateProcess(List<NODE_CONFIG> nodeConfigList,
                                 List<LINE_CONFIG> lineConfigList,
                                 SfProcessEngineConfig processEngineConfig) {

    }
}
