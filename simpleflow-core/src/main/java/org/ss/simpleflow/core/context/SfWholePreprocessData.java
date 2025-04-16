package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfWholePreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {
    SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> getMainProcessPreprocessData();

    void setMainProcessPreprocessData(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData);

    SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] getSubProcessPreprocessDataArray();

    void setSubProcessPreprocessDataArray(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray);
}
