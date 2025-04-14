package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManager<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>>
        implements SfValidateManager<NI, EI, PCI, NC, EC, PC> {

    private final SfDefaultBasicValidator<NI, EI, PCI, NC, EC, PC> basicValidator;
    private final SfDefaultBusinessValidator<NI, EI, PCI, NC, EC, PC> businessValidator;

    SfDefaultValidateManager(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> nodeConfigCustomValidator,
                             SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> edgeConfigCustomValidator,
                             SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC> processConfigCustomValidate) {
        this.basicValidator = new SfDefaultBasicValidator<>(nodeConfigCustomValidator,
                                                            edgeConfigCustomValidator,
                                                            processConfigCustomValidate);
        this.businessValidator = new SfDefaultBusinessValidator<>();
    }

    @Override
    public void validate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                         SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> validationWholeContext,
                         SfProcessPreprocessConfig processPreprocessConfig) {
        basicValidator.basicValidate(wholeProcessConfig, validationWholeContext, processPreprocessConfig);
        businessValidator.businessValidate(wholeProcessConfig,
                                           validationWholeContext,
                                           processPreprocessConfig);
    }

}
