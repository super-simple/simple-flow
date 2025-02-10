package org.ss.simpleflow.core.processengine;

import org.ss.simpleflow.core.context.SfExecutionWholeContext;
import org.ss.simpleflow.core.context.SfProcessExecuteResult;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.Map;

public interface SfProcessEngine<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> wholeContext,
                                           PEI executionId,
                                           Map<String, Object> params,
                                           Map<String, Object> env);

    SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> wholeContext,
                                           Map<String, Object> params,
                                           Map<String, Object> env);

    SfProcessExecuteResult<PEI> runProcess(SfExecutionWholeContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> wholeContext,
                                           Map<String, Object> params);

}
