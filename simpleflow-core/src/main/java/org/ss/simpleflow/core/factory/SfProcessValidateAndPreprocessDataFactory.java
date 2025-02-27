package org.ss.simpleflow.core.factory;

import org.ss.simpleflow.core.context.SfProcessPreprocessData;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.context.SfValidationWholeContext;
import org.ss.simpleflow.core.context.SfWholePreprocessData;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfProcessValidateAndPreprocessDataFactory<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> createWholePreprocessData();

    SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> createProcessPreprocessData();

    SfValidationWholeContext<NI, EI, PCI, NC, EC, PC> createValidationWholeContext();

    SfValidationProcessContext<NI, EI, PCI, NC, EC, PC> createValidationProcessContext();

}
