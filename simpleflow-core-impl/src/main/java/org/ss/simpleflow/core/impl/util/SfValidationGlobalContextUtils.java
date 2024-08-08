package org.ss.simpleflow.core.impl.util;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfValidationGlobalContext;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.factory.SfContextFactory;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;

import java.util.ArrayList;
import java.util.List;

public class SfValidationGlobalContextUtils {

    public static <NI, EI, PCI,
            NC extends SfAbstractNodeConfig<NI, PCI>,
            EC extends SfAbstractEdgeConfig<EI, NI>,
            PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
            PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
            NEI, EEI, PEI>
    SfValidationGlobalContext<NI, EI, PCI,
            NC, EC,
            PCG, PC, NEI,
            EEI, PEI> createValidationGlobalContext(
            PC processConfig,
            SfContextFactory<NI, EI, PCI,
                    NC, EC,
                    PCG, PC,
                    NEI, EEI, PEI> contextFactory) {
        SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext = contextFactory.createValidationGlobalContext();
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainProcessValidationContext = contextFactory.createProcessValidationContext();
        validationGlobalContext.setMainProcessValidationContext(mainProcessValidationContext);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = new ArrayList<>(
                    size);
            validationGlobalContext.setSubValidationProcessContextList(subExecutionProcessContextList);
            for (int i = 0; i < size; i++) {
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> processValidationContext = contextFactory.createProcessValidationContext();

            }
        }

        return validationGlobalContext;
    }

}
