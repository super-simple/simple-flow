package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfExecutionProcessContext;
import org.ss.simpleflow.core.context.SfExecutionProcessInternalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.impl.util.StackUtils;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.*;

public class SfDefaultBusinessValidator<NI, EI, PCI,
        NC extends SfAbstractNodeConfig<NI, PCI>,
        EC extends SfAbstractEdgeConfig<EI, NI>,
        PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
        PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
        NEI, EEI, PEI> {

    public void businessValidate(PC processConfig,
                                 SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
                                 SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext,
                                 SfProcessEngineConfig processEngineConfig) {
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
        if (CollectionUtils.isNullOrEmpty(nodeConfigList)) {

        }
    }

    private void collectReferencedSubProcessAndValidate(
            SfExecutionGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> executionGlobalContext,
            SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionProcessContext,
            List<PCG> subProcessConfigList,
            PC processConfig,
            SfProcessContext<NI, EI, PCI, NC, EC, PCG, PC, PEI> processContext,
            SfProcessEngineConfig processEngineConfig) {
        SfExecutionProcessInternalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> mainExecutionInternalContext = mainExecutionProcessContext.getExecutionInternalContext();
        Set<PCI> mainSubProcessConfigIdSet = mainExecutionInternalContext.getSubProcessConfigIdSet();
        if (CollectionUtils.isNotEmpty(mainSubProcessConfigIdSet)) {
            Set<PCI> referencedSubProcessConfigIdSet = new HashSet<>();
            Map<PCI, Set<PCI>> collectSubProcessConfigIdMap = new HashMap<>();
            int size = subProcessConfigList.size();

            List<SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subExecutionProcessContextList = executionGlobalContext.getSubExecutionProcessContextList();
            for (int i = 0; i < size; i++) {
                PCG processConfigGraph = subProcessConfigList.get(i);
                SfExecutionProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> subExecutionProcessContext = subExecutionProcessContextList.get(
                        i);
                collectSubProcessConfigIdMap.put(processConfigGraph.getId(),
                                                 subExecutionProcessContext.getExecutionInternalContext().getSubProcessConfigIdSet());
            }

            Deque<PCI> stack = new ArrayDeque<>();
            StackUtils.pushAllToStack(stack, mainSubProcessConfigIdSet);
            while (!stack.isEmpty()) {
                PCI pop = stack.pop();
                if (collectSubProcessConfigIdMap.containsKey(pop)) {
                    referencedSubProcessConfigIdSet.add(pop);
                    Set<PCI> processConfigIdSet = collectSubProcessConfigIdMap.get(pop);
                    StackUtils.pushAllToStack(stack, processConfigIdSet);
                } else {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_SUB_PROCESS,
                                                       null,
                                                       null,
                                                       processContext,
                                                       processEngineConfig);
                }
            }

            executionGlobalContext.setReferencedSubProcessConfigIdSet(referencedSubProcessConfigIdSet);

            collectSubProcessConfigIdMap.put(processConfig.getId(), mainSubProcessConfigIdSet);
            for (Map.Entry<PCI, Set<PCI>> entry : collectSubProcessConfigIdMap.entrySet()) {
                PCI processConfigId = entry.getKey();
                Set<PCI> referencedProcessConfigIdSet = entry.getValue();
                if (CollectionUtils.isNotEmpty(referencedProcessConfigIdSet)) {
                    for (PCI referencedProcessConfigId : referencedProcessConfigIdSet) {
                        Set<PCI> processConfigIdSet = collectSubProcessConfigIdMap.get(
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
