package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.impl.exceptional.SfLineConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfLineConfigExceptionCode;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfNodeConfigExceptionCode;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultBasicValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
        PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>> {

    private final SfDefaultNodeConfigValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> nodeConfigValidator = new SfDefaultNodeConfigValidator<>();
    private final SfDefaultLineConfigValidator<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH> lineConfigValidator = new SfDefaultLineConfigValidator<>();
    private final SfNodeConfigCustomValidator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeConfigCustomValidator;
    private final SfLineConfigCustomValidator<NODE_ID, LINE_ID, LINE_CONFIG> lineConfigCustomValidator;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator<NODE_ID, PROCESS_CONFIG_ID, NODE_CONFIG> nodeConfigCustomValidator,
                                   SfLineConfigCustomValidator<NODE_ID, LINE_ID, LINE_CONFIG> lineConfigCustomValidator) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.lineConfigCustomValidator = lineConfigCustomValidator;
    }

    public void basicValidate(PROCESS_CONFIG_GRAPH processConfigGraph) {
        List<NODE_CONFIG> nodeConfigList = processConfigGraph.getNodeConfigList();
        Set<NODE_ID> nodeIdSet = new HashSet<>(nodeConfigList.size());
        for (NODE_CONFIG nodeConfig : nodeConfigList) {
            NODE_ID nodeId = nodeConfig.getId();
            if (nodeIdSet.contains(nodeId)) {
                throw new SfNodeConfigException(SfNodeConfigExceptionCode.ID_REPEAT, nodeConfig, processConfigGraph);
            }
            nodeIdSet.add(nodeId);

            nodeConfigValidator.validateSingleNodeConfig(nodeConfig, processConfigGraph);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.validateSingleNodeConfig(nodeConfig);
            }
        }


        List<LINE_CONFIG> lineConfigList = processConfigGraph.getLineConfigList();
        Set<LINE_ID> lineIdSet = new HashSet<>(lineConfigList.size());
        for (LINE_CONFIG lineConfig : lineConfigList) {
            LINE_ID lineId = lineConfig.getId();
            if (lineIdSet.contains(lineId)) {
                throw new SfLineConfigException(SfLineConfigExceptionCode.ID_REPEAT, lineConfig, processConfigGraph);
            }
            lineIdSet.add(lineId);

            lineConfigValidator.validateSingleLineConfig(lineConfig, processConfigGraph);
            if (lineConfigCustomValidator != null) {
                lineConfigCustomValidator.validateSingleLineConfig(lineConfig);
            }
        }
    }

}
