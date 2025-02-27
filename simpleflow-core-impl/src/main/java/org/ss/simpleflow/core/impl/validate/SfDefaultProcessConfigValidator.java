package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultProcessConfigValidator
        <NI, EI, PCI,
                NC extends SfAbstractNodeConfig<NI, PCI>,
                EC extends SfAbstractEdgeConfig<EI, NI>,
                PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {
    public void basicValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                              SfProcessEngineConfig processEngineConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        PCI processConfigId = mainProcessConfig.getId();
        if (processConfigId == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                               mainProcessConfig,
                                               processEngineConfig);
        }

        List<PC> subProcessConfigList = wholeProcessConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            Set<PCI> processConfigIdSet = new HashSet<>(subProcessConfigList.size() + 1);
            processConfigIdSet.add(processConfigId);
            for (PC subProcessConfig : subProcessConfigList) {
                PCI subProcessConfigId = subProcessConfig.getId();
                if (subProcessConfigId == null) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                                       subProcessConfig,
                                                       processEngineConfig);
                }
                if (processConfigIdSet.contains(subProcessConfigId)) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.ID_REPEAT,
                                                       subProcessConfig,
                                                       processEngineConfig);
                } else {
                    processConfigIdSet.add(subProcessConfigId);
                }
            }
        }
    }


}
