package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfExecutionGlobalContext;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.List;

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

}
