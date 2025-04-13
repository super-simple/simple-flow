package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public abstract class SfWholePreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    protected SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData;

    protected SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray;

    public SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> getMainProcessPreprocessData() {
        return mainProcessPreprocessData;
    }

    public void setMainProcessPreprocessData(SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData) {
        this.mainProcessPreprocessData = mainProcessPreprocessData;
    }

    public SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] getSubProcessPreprocessDataArray() {
        return subProcessPreprocessDataArray;
    }

    public void setSubProcessPreprocessDataArray(SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>[] subProcessPreprocessDataArray) {
        this.subProcessPreprocessDataArray = subProcessPreprocessDataArray;
    }

}
