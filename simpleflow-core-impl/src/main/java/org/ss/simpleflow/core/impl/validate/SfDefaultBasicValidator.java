package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.line.SfLineConfig;
import org.ss.simpleflow.core.node.SfNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.validate.SfLineConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultBasicValidator {

    private final SfDefaultNodeConfigValidator nodeConfigValidator = new SfDefaultNodeConfigValidator();
    private final SfDefaultLineConfigValidator lineConfigValidator = new SfDefaultLineConfigValidator();
    private final SfNodeConfigCustomValidator nodeConfigCustomValidator;
    private final SfLineConfigCustomValidator lineConfigCustomValidator;

    public SfDefaultBasicValidator(SfNodeConfigCustomValidator nodeConfigCustomValidator,
                                   SfLineConfigCustomValidator lineConfigCustomValidator) {
        this.nodeConfigCustomValidator = nodeConfigCustomValidator;
        this.lineConfigCustomValidator = lineConfigCustomValidator;
    }

    public void basicValidate(SfProcessConfig processConfig) {
        List<SfLineConfig> lineConfigList = processConfig.getLineConfigList();
        Set<String> lineIdSet = new HashSet<>(lineConfigList.size());
        for (SfLineConfig lineConfig : lineConfigList) {
            lineConfigValidator.validateSingleLineConfig(lineConfig);
            if (lineConfigCustomValidator != null) {
                lineConfigCustomValidator.validateSingleLineConfig(lineConfig);
            }
        }

        List<SfNodeConfig> nodeConfigList = processConfig.getNodeConfigList();
        for (SfNodeConfig nodeConfig : nodeConfigList) {
            nodeConfigValidator.validateSingleNodeConfig(nodeConfig);
            if (nodeConfigCustomValidator != null) {
                nodeConfigCustomValidator.validateSingleNodeConfig(nodeConfig);
            }
        }

    }

}
