package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfValidateManagerFactory;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManagerFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> implements SfValidateManagerFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> {
    @Override
    public SfValidateManager<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> buildValidateManager(
            SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> nodeConfigCustomValidator,
            SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC, PEI> edgeConfigCustomValidator,
            SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC, PEI> processConfigCustomValidate) {
        return new SfDefaultValidateManager<>(nodeConfigCustomValidator,
                                              edgeConfigCustomValidator,
                                              processConfigCustomValidate);
    }
}
