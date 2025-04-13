package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public class SfWholeProcessConfig<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {

    private PC mainProcessConfig;
    private PC[] subProcessConfigArray;

    public PC getMainProcessConfig() {
        return mainProcessConfig;
    }

    public void setMainProcessConfig(PC mainProcessConfig) {
        this.mainProcessConfig = mainProcessConfig;
    }

    public PC[] getSubProcessConfigArray() {
        return subProcessConfigArray;
    }

    public void setSubProcessConfigArray(PC[] subProcessConfigArray) {
        this.subProcessConfigArray = subProcessConfigArray;
    }

}
