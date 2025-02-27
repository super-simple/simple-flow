package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.validate.SfEdgeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfNodeConfigCustomValidator;
import org.ss.simpleflow.core.validate.SfProcessConfigCustomValidate;
import org.ss.simpleflow.core.validate.SfValidateManager;

public interface SfValidateManagerFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    SfValidateManager<NI, EI, PCI, NC, EC, PC>
    buildValidateManager(SfNodeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> nodeConfigCustomValidator,
                         SfEdgeConfigCustomValidator<NI, EI, PCI, NC, EC, PC> edgeConfigCustomValidator,
                         SfProcessConfigCustomValidate<NI, EI, PCI, NC, EC, PC> processConfigCustomValidate);

}
