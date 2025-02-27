package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManager<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI>
        implements SfValidateManager<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {

    private final SfDefaultBasicValidator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> basicValidator;
    private final SfDefaultBusinessValidator<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> businessValidator;

    SfDefaultValidateManager(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> nodeConfigCustomValidator,
                             SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> edgeConfigCustomValidator,
                             SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC, PEI> processConfigCustomValidate) {
        this.basicValidator = new SfDefaultBasicValidator<>(nodeConfigCustomValidator,
                                                            edgeConfigCustomValidator,
                                                            processConfigCustomValidate);
        this.businessValidator = new SfDefaultBusinessValidator<>();
    }

    @Override
    public void validate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                         SfProcessContext<NI, EI, PCI,
                                 NC, EC,
                                 PC, PEI> processContext,
                         SfValidationWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validationGlobalContext,
                         SfProcessEngineConfig processEngineConfig) {
        basicValidator.basicValidate(wholeProcessConfig, processContext, validationGlobalContext, processEngineConfig);
        businessValidator.businessValidate(wholeProcessConfig,
                                           processContext,
                                           validationGlobalContext,
                                           processEngineConfig);
    }

}
