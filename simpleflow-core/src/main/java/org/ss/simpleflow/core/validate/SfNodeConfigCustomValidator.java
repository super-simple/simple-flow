package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

public interface SfNodeConfigCustomValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        PEI> {
    void customValidate(NC nodeConfig,
                        PC processConfig,
                        SfProcessContext<NI, EI, PCI,
                                NC, EC,
                                PC, PEI> processContext,
                        SfProcessEngineConfig processEngineConfig);
}
