package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfExecutionWholeContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;

public interface SfProcessValidateAndPreprocess<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    SfExecutionWholeContext<NI, EI, PCI, NC, EC, PC, NEI, EEI, PEI> validateAndPreprocess(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig);

}
