package org.ss.simpleflow.core.processengine;


import org.ss.simpleflow.core.context.SfProcessExecutionResult;
import org.ss.simpleflow.core.context.SfWholePreprocessData;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;

import java.util.Map;

public interface SfProcessEngine<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        NEI, EEI, PEI> {

    SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                             Map<String, Object> params);

    SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                             Map<String, Object> params,
                                             Map<String, Object> processVariable);

    SfProcessExecutionResult<PEI> runProcess(SfWholePreprocessData<NI, EI, PCI, NC, EC, PC> wholePreprocessData,
                                             Map<String, Object> params,
                                             Map<String, Object> processVariable,
                                             PEI processExecutionId);

}
