package org.ss.simpleflow.core.impl.util;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfExecutionProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfContextFactory;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.ArrayList;
import java.util.List;

public class SfExecutionGlobalContextUtils {

    public static <NI, EI, PCI,
            NC extends SfAbstractNodeConfig<NI, PCI>,
            EC extends SfAbstractEdgeConfig<EI, NI>,
            PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
            PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
            NEI, EEI, PEI>
    SfExecutionGlobalContext<NI, EI, PCI,
            NC, EC,
            PCG, PC, NEI,
            EEI, PEI> createGlobalContext(
            PC processConfig,
            SfContextFactory<NI, EI, PCI,
                    NC, EC,
                    PCG, PC,
                    NEI, EEI, PEI> contextFactory) {
        SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext = contextFactory.createExecutionGlobalContext();
        SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext = contextFactory.createExecutionProcessContext();
        executionGlobalContext.setMainExecutionProcessContext(mainExecutionProcessContext);
        mainExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
        mainExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            executionGlobalContext.setSubExecutionProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = contextFactory.createExecutionProcessContext();
                subExecutionProcessContextList.add(subExecutionProcessContext);

                subExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
                subExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());
            }
        }

        return executionGlobalContext;
    }

}
