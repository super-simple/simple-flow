package org.ss.simpleflow.core.context;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

public interface SfProcessContext<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        PEI> extends SfVariableContext {

    void setRoot(boolean root);

    boolean isRoot();

    void setRootProcessContext(SfProcessContext<NI, EI, PCI,
            NC, EC, PC, PEI> rootProcessContext);

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getRootProcessContext();

    void setParentProcessContext(SfProcessContext<NI, EI, PCI,
            NC, EC, PC, PEI> processContext);

    SfProcessContext<NI, EI, PCI, NC, EC, PC, PEI> getParentProcessContext();

    void setProcessExecutionId(PEI processExecutionId);

    PEI getProcessExecutionId();

    void setProcessConfig(PC processConfig);

    PC getProcessConfig();
}
