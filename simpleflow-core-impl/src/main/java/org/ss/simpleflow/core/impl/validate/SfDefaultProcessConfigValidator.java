package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultProcessConfigValidator
        <NI, EI, PCI,
                NC extends SfAbstractNodeConfig<NI, PCI>,
                EC extends SfAbstractEdgeConfig<EI, NI>,
                PCG extends SfProcessConfigGraph<NI, EI, PCI, NC, EC>,
                PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC, PCG>,
                PEI> {
    public void basicValidate(PC processConfig,
                              SfProcessContext<NI, EI, PCI,
                                      NC, EC,
                                      PCG, PC, PEI> processContext,
                              SfProcessEngineConfig processEngineConfig) {
        PCI processConfigId = processConfig.getId();
        if (processConfigId == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                               processConfig,
                                               null,
                                               processContext,
                                               processEngineConfig);
        }

        List<PCG> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            Set<PCI> processConfigIdSet = new HashSet<>(subProcessConfigList.size() + 1);
            processConfigIdSet.add(processConfigId);
            for (PCG processConfigGraph : subProcessConfigList) {
                PCI subProcessConfigId = processConfigGraph.getId();
                if (subProcessConfigId == null) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                                       processConfig,
                                                       processConfigGraph,
                                                       processContext,
                                                       processEngineConfig);
                }
                if (processConfigIdSet.contains(subProcessConfigId)) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.ID_REPEAT,
                                                       processConfig,
                                                       processConfigGraph,
                                                       processContext,
                                                       processEngineConfig);
                } else {
                    processConfigIdSet.add(subProcessConfigId);
                }
            }
        }
    }


}
