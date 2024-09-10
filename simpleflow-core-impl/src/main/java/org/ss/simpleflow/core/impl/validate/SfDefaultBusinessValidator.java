package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.context.SfValidationGlobalContext;
import org.ss.simpleflow.core.context.SfValidationProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.edge.SfEdgeIndexEntry;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.node.SfNodeIndexEntry;
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
                                 SfValidationGlobalContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationGlobalContext,
                                 SfProcessEngineConfig processEngineConfig) {

        validateProcessCircularReference(processContext,
                                         validationGlobalContext,
                                         processEngineConfig);

        List<NC> nodeConfigList = processConfig.getNodeConfigList();
        List<EC> edgeConfigList = processConfig.getEdgeConfigList();

        validateProcess(nodeConfigList,
                        edgeConfigList,
                        processContext,
                        validationGlobalContext.getMainProcessValidationContext(),
                        processEngineConfig);

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();

        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            int size = subProcessConfigList.size();
            List<SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI>> subValidationProcessContextList = validationGlobalContext.getSubValidationProcessContextList();
            for (int i = 0; i < size; i++) {
                PCG processConfigGraph = subProcessConfigList.get(i);
                SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationProcessContext = subValidationProcessContextList.get(
                        i);
                List<NC> subProcessNodeConfigList = processConfigGraph.getNodeConfigList();
                List<EC> subProcessEdgeConfigList = processConfigGraph.getEdgeConfigList();
                validateProcess(subProcessNodeConfigList,
                                subProcessEdgeConfigList,
                                processContext,
                                validationProcessContext, processEngineConfig);
            }
        }
    }


    private void validateProcess(List<NC> nodeConfigList,
                                 List<EC> edgeConfigList,
                                 SfProcessContext<NI, EI, PCI,
                                         NC, EC,
                                         PCG, PC, PEI> processContext,
                                 SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationProcessContext,
                                 SfProcessEngineConfig processEngineConfig) {
        generateIndex(nodeConfigList, edgeConfigList, validationProcessContext);

        List<SfEdgeIndexEntry> edgeIndexEntryList = validationProcessContext.getEdgeIndexEntryList();
        int nodeConfigListSize = nodeConfigList.size();
        CollectionUtils.collect(edgeIndexEntryList, )
    }

    private void generateIndex(
            List<NC> nodeConfigList,
            List<EC> edgeConfigList,
            SfValidationProcessContext<NI, EI, PCI, NC, EC, PCG, PC, NEI, EEI, PEI> validationProcessContext) {
        int nodeConfigListSize = nodeConfigList.size();
        List<SfNodeIndexEntry> nodeIndexEntryList = new ArrayList<>(nodeConfigListSize);
        Map<NI, Integer> nodeConfigIndexMap = new HashMap<>(nodeConfigListSize);
        for (int i = 0; i < nodeConfigListSize; i++) {
            SfNodeIndexEntry nodeIndexEntry = new SfNodeIndexEntry();
            nodeIndexEntry.setNodeIndex(i);
            nodeIndexEntryList.add(nodeIndexEntry);
            nodeConfigIndexMap.put(nodeConfigList.get(i).getId(), i);
        }

        validationProcessContext.setNodeIndexEntryList(nodeIndexEntryList);

        if (CollectionUtils.isNullOrEmpty(edgeConfigList)) {
            return;
        }

        int edgeConfigListSize = edgeConfigList.size();
        List<SfEdgeIndexEntry> edgeIndexEntryList = new ArrayList<>(edgeConfigListSize);
        for (int i = 0; i < edgeConfigListSize; i++) {
            EC edgeConfig = edgeConfigList.get(i);
            SfEdgeIndexEntry edgeIndexEntry = new SfEdgeIndexEntry();

            edgeIndexEntry.setEdgeTypeIndex(edgeConfig.fetchEdgeTypeIndex());

            edgeIndexEntryList.add(edgeIndexEntry);
            edgeIndexEntry.setEdgeIndex(i);

            NI fromNodeId = edgeConfig.getFromNodeId();
            NI toNodeId = edgeConfig.getToNodeId();

            edgeIndexEntry.setFromNodeConfigIndex(nodeConfigIndexMap.get(fromNodeId));
            edgeIndexEntry.setToNodeConfigIndex(nodeConfigIndexMap.get(toNodeId));
        }

        validationProcessContext.setEdgeIndexEntryList(edgeIndexEntryList);
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
