package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigExceptionCode;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultBasicValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
        PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
        PROCESS_EXECUTION_ID> {

    private final SfDefaultNodeConfigValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigValidator;
    private final SfDefaultLineConfigValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigValidator;
    private final SfDefaultProcessConfigValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigValidator;
    private final SfNodeConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator;
    private final SfLineConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigCustomValidator;
    private final SfProcessConfigCustomValidate<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> nodeConfigCustomValidator,
                                   SfLineConfigCustomValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> lineConfigCustomValidator,
                                   SfProcessConfigCustomValidate<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processConfigCustomValidate) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.lineConfigCustomValidator = lineConfigCustomValidator;
        this.processConfigCustomValidate = processConfigCustomValidate;

        nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
        lineConfigValidator = new SfDefaultLineConfigValidator<>();
        processConfigValidator = new SfDefaultProcessConfigValidator<>();
    }

    public void preValidate(PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        processConfigValidator.preValidate(processConfig, processContext, processEngineConfig);


        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        Set<NODE_ID> nodeIdSet = new HashSet<>(nodeConfigList.size());
        for (NODE_CONFIG nodeConfig : nodeConfigList) {
            NODE_ID nodeId = nodeConfig.getId();
            if (nodeIdSet.contains(nodeId)) {
                throw new SfNodeConfigException(SfNodeConfigExceptionCode.ID_REPEAT,
                                                nodeConfig,
                                                processConfig,
                                                null,
                                                processContext,
                                                processEngineConfig);
            }
            nodeIdSet.add(nodeId);

            nodeConfigValidator.preValidate(nodeConfig, processConfig, processContext, processEngineConfig);
        }

        List<LINE_CONFIG> lineConfigList = processConfig.getLineConfigList();
        Set<LINE_ID> lineIdSet = new HashSet<>(lineConfigList.size());
        for (LINE_CONFIG lineConfig : lineConfigList) {
            LINE_ID lineId = lineConfig.getId();
            if (lineIdSet.contains(lineId)) {
                throw new SfLineConfigException(SfLineConfigExceptionCode.ID_REPEAT,
                                                lineConfig,
                                                processConfig,
                                                null,
                                                processContext,
                                                processEngineConfig);
            }
            lineIdSet.add(lineId);

            lineConfigValidator.preValidate(lineConfig, processConfig, processContext, processEngineConfig);
        }
    }

    public void basicValidate(PROCESS_CONFIG processConfig,
                              SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                              SfProcessEngineConfig processEngineConfig) {
        List<NODE_CONFIG> nodeConfigList = processConfig.getNodeConfigList();
        for (NODE_CONFIG nodeConfig : nodeConfigList) {
            nodeConfigValidator.validate(nodeConfig, processConfig, processContext, processEngineConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.customValidate(nodeConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }
        }

        List<LINE_CONFIG> lineConfigList = processConfig.getLineConfigList();
        for (LINE_CONFIG lineConfig : lineConfigList) {
            lineConfigValidator.validate(lineConfig, processConfig, processContext, processEngineConfig);
            if (lineConfigCustomValidator != null) {
                lineConfigCustomValidator.customValidate(lineConfig,
                                                         processConfig,
                                                         processContext,
                                                         processEngineConfig);
            }
        }
    }

}
