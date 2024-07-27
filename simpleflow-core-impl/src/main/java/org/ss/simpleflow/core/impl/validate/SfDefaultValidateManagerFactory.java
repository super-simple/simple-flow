package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfValidateManagerFactory;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public class SfDefaultValidateManagerFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> implements SfValidateManagerFactory<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> {
    @Override
    public SfValidateManager<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> buildValidateManager(
            SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> nodeConfigCustomValidator,
            SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PCG, PC, PEI> edgeConfigCustomValidator,
            SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PCG, PC, PEI> processConfigCustomValidate) {
        return new SfDefaultValidateManager<>(nodeConfigCustomValidator,
                                              edgeConfigCustomValidator,
                                              processConfigCustomValidate);
    }
}
