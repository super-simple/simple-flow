package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.util.List;

public class SfWholeProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    private PC mainProcessConfig;
    private List<PC> subProcessConfigList;

    public PC getMainProcessConfig() {
        return mainProcessConfig;
    }

    public void setMainProcessConfig(PC mainProcessConfig) {
        this.mainProcessConfig = mainProcessConfig;
    }

    public List<PC> getSubProcessConfigList() {
        return subProcessConfigList;
    }

    public void setSubProcessConfigList(List<PC> subProcessConfigList) {
        this.subProcessConfigList = subProcessConfigList;
    }
}
