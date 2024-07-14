package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

import java.util.List;

public class SfDefaultValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID>
        implements SfValidateManager<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> {

    private final SfDefaultBasicValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> basicValidator;

    SfDefaultValidateManager(SfNodeConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator,
                             SfLineConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigCustomValidator,
                             SfProcessConfigCustomValidate<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate) {
        basicValidator = new SfDefaultBasicValidator<>(nodeConfigCustomValidator,
                                                       lineConfigCustomValidator,
                                                       processConfigCustomValidate);
    }

    @Override
    public void preValidate(PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        basicValidator.preValidate(processConfig, processContext, processEngineConfig);
    }

    @Override
    public void validate(PROCESS_CONFIG processConfig,
                         SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                                 NODE_CONFIG, LINE_CONFIG,
                                 PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                         SfProcessEngineConfig processEngineConfig) {

        basicValidator.basicValidate(processConfig, processContext, processEngineConfig);

        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        List<LINE_CONFIG> lineConfigList = processConfig.getLineConfigList();

        validateProcess(nodeConfigList, lineConfigList, processContext, processEngineConfig);

        validateSubProcessConfig(processConfig, processContext, processEngineConfig);

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();

        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                List<NODE_CONFIG> subProcessNodeConfigList = processConfigGraph.getNodeConfigList();
                List<LINE_CONFIG> subProcessLineConfigList = processConfigGraph.getLineConfigList();
                validateProcess(subProcessNodeConfigList,
                                subProcessLineConfigList,
                                processContext,
                                processEngineConfig);
            }
        }
    }

    private void validateSubProcessConfig(PROCESS_CONFIG processConfig,
                                          SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                                                  NODE_CONFIG, LINE_CONFIG,
                                                  PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                          SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
    }


    private void validateProcess(List<NODE_CONFIG> nodeConfigList,
                                 List<LINE_CONFIG> lineConfigList,
                                 SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                                         NODE_CONFIG, LINE_CONFIG,
                                         PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                                 SfProcessEngineConfig processEngineConfig) {
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {

        }
    }
}
