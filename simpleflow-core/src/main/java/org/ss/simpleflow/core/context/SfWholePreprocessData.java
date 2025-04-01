package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.List;

public abstract class SfWholePreprocessData<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    protected SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData;

    protected List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> subProcessPreprocessDataList;

    public SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> getMainProcessPreprocessData() {
        return mainProcessPreprocessData;
    }

    public void setMainProcessPreprocessData(SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC> mainProcessPreprocessData) {
        this.mainProcessPreprocessData = mainProcessPreprocessData;
    }

    public List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> getSubProcessPreprocessDataList() {
        return subProcessPreprocessDataList;
    }

    public void setSubProcessPreprocessDataList(List<SfProcessPreprocessData<NI, EI, PCI, NC, EC, PC>> subProcessPreprocessDataList) {
        this.subProcessPreprocessDataList = subProcessPreprocessDataList;
    }

}
