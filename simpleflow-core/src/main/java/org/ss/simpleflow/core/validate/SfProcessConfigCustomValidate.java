package org.ss.simpleflow.core.validate;

import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

/**
 * 校验
 */
public interface SfProcessConfigCustomValidate<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,

        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>,
        PEI> {

    void customValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                        SfProcessContext<NI, EI, PCI,
                                NC, EC,
                                PC, PEI> processContext,
                        SfProcessEngineConfig processEngineConfig);

}
