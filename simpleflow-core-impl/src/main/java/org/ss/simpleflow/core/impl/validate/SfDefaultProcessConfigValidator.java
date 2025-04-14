package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfAbstractProcessConfig;
import org.ss.simpleflow.core.processconfig.SfWholeProcessConfig;
import org.ss.simpleflow.core.processengine.SfProcessPreprocessConfig;

import java.util.HashSet;
import java.util.Set;

public class SfDefaultProcessConfigValidator
        <NI, EI, PCI,
                NC extends SfAbstractNodeConfig<NI, PCI>,
                EC extends SfAbstractEdgeConfig<EI, NI>,
                PC extends SfAbstractProcessConfig<NI, EI, PCI, NC, EC>> {
    public void basicValidate(SfWholeProcessConfig<NI, EI, PCI, NC, EC, PC> wholeProcessConfig,
                              SfProcessPreprocessConfig processPreprocessConfig) {
        PC mainProcessConfig = wholeProcessConfig.getMainProcessConfig();
        PCI processConfigId = getPci(processPreprocessConfig, mainProcessConfig);

        PC[] subProcessConfigArray = wholeProcessConfig.getSubProcessConfigArray();
        int length = subProcessConfigArray.length;
        if (length == 0) {
            Set<PCI> processConfigIdSet = new HashSet<>(length + 1);
            processConfigIdSet.add(processConfigId);
            for (PC subProcessConfig : subProcessConfigArray) {
                PCI subProcessConfigId = getPci(processPreprocessConfig, subProcessConfig);
                if (processConfigIdSet.contains(subProcessConfigId)) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.ID_REPEAT,
                                                       subProcessConfig,
                                                       processPreprocessConfig);
                } else {
                    processConfigIdSet.add(subProcessConfigId);
                }
            }
        }
    }

    private PCI getPci(
            SfProcessPreprocessConfig processPreprocessConfig,
            PC mainProcessConfig) {
        PCI processConfigId = mainProcessConfig.getId();
        if (processConfigId == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                               mainProcessConfig,
                                               processPreprocessConfig);
        }
        return processConfigId;
    }


}
