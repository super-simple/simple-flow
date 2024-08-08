package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfValidationGlobalContext;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SfDefaultBusinessValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    public void businessValidate(PC processConfig,
                                 SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                                 SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext,
                                 SfProcessEngineConfig processEngineConfig) {

        validateProcessCircularReference(processContext,
                                         validationGlobalContext,
                                         processEngineConfig);

        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        List<EC> edgeConfigList = processConfig.getEdgeConfigList();

        validateProcess(nodeConfigList, edgeConfigList, processContext, processEngineConfig);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();

        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            for (PCG processConfigGraph : subProcessConfigList) {
                List<NC> subProcessNodeConfigList = processConfigGraph.getNodeConfigList();
                List<EC> subProcessEdgeConfigList = processConfigGraph.getEdgeConfigList();
                validateProcess(subProcessNodeConfigList,
                                subProcessEdgeConfigList,
                                processContext,
                                processEngineConfig);
            }
        }

    }


    private void validateProcess(List<NC> nodeConfigList,
                                 List<EC> edgeConfigList,
                                 SfProcessContext<NI, EI, PCI,
                                         NC, EC,
                                         PCG, PC, PEI> processContext,
                                 SfProcessEngineConfig processEngineConfig) {

    }

    private void validateProcessCircularReference(
            SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
            SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext,
            SfProcessEngineConfig processEngineConfig) {
        SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainProcessValidationContext = validationGlobalContext.getMainProcessValidationContext();
        Set<PCI> mainSubProcessConfigIdSet = mainProcessValidationContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Map<PCI, Set<PCI>> subProcessContainProcessConfigIdMap = validationGlobalContext.getSubProcessContainProcessConfigIdMap();

            for (Map.Entry<PCI, Set<PCI>> entry : subProcessContainProcessConfigIdMap.entrySet()) {
                PCI processConfigId = entry.getKey();
                Set<PCI> referencedProcessConfigIdSet = entry.getValue();
                if (CollectionUtils.isNotEmpty(referencedProcessConfigIdSet)) {
                    for (PCI referencedProcessConfigId : referencedProcessConfigIdSet) {
                        Set<PCI> processConfigIdSet = subProcessContainProcessConfigIdMap.get(
                                referencedProcessConfigId);
                        if (CollectionUtils.isNotEmpty(processConfigIdSet)) {
                            if (processConfigIdSet.contains(processConfigId)) {
                                throw new SfProcessConfigException(SfProcessConfigExceptionCode.CIRCULAR_REFERENCE,
                                                                   null,
                                                                   null,
                                                                   processContext,
                                                                   processEngineConfig);
                            }
                        }
                    }
                }
            }
        }
    }

}
