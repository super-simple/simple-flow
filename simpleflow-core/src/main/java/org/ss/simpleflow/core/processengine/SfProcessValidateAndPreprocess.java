package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfWholePreprocessData;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;

public interface SfProcessValidateAndPreprocess<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    SfWholePreprocessData<NI, EI, PCI, NC, EC, PC>
    validateAndPreprocess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig);

}
