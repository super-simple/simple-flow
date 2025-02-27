package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;
import org.ss.simpleflow.core.processengine.SfProcessValidateAndPreprocess;
import org.ss.simpleflow.core.validate.SfValidateManager;

public interface SfProcessValidateAndPreprocessFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    SfProcessValidateAndPreprocess<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI>
    createProcessValidateAndPreprocess(SfProcessEngineConfig processEngineConfig,
                                       SfValidateManager<NI, EI, PCI, NC, EC, PC> validateManager,
                                       SfContextFactory<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> contextFactory);
}
