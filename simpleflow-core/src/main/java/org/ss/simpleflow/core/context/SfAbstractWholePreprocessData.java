package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public abstract class SfAbstractWholePreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> implements SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> {

    protected SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData;

    protected SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray;

    @Override
    public SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> getMainProcessPreprocessData() {
        return mainProcessPreprocessData;
    }

    @Override
    public void setMainProcessPreprocessData(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData) {
        this.mainProcessPreprocessData = mainProcessPreprocessData;
    }

    @Override
    public SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] getSubProcessPreprocessDataArray() {
        return subProcessPreprocessDataArray;
    }

    @Override
    public void setSubProcessPreprocessDataArray(SfAbstractProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray) {
        this.subProcessPreprocessDataArray = subProcessPreprocessDataArray;
    }

}
