package org.ss.simpleflow.core.impl.validate;

import org.ss.simpleflow.common.CollectionUtils;
import org.ss.simpleflow.core.context.SfProcessContext;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigException;
import org.ss.simpleflow.core.impl.exceptional.SfProcessConfigExceptionCode;
import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfig;
import org.ss.simpleflow.core.processconfig.SfProcessConfigGraph;
import org.ss.simpleflow.core.processengine.SfProcessEngineConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SfDefaultProcessConfigValidator
        <NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
                LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>,
                PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG>,
                PROCESS_CONFIG extends SfProcessConfig<NODE_ID, LINE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, LINE_CONFIG, PROCESS_CONFIG_GRAPH>,
                PROCESS_EXECUTION_ID> {
    public void preValidate(PROCESS_CONFIG processConfig,
                            SfProcessContext<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
                                    NODE_CONFIG, LINE_CONFIG,
                                    PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, PROCESS_EXECUTION_ID> processContext,
                            SfProcessEngineConfig processEngineConfig) {
        PROCESS_CONFIG_ID processConfigId = processConfig.getId();
        if (processConfigId == null) {
            throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                               processConfig,
                                               null,
                                               processContext,
                                               processEngineConfig);
        }

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            Set<PROCESS_CONFIG_ID> subProcessConfigIdSet = new HashSet<>(subProcessConfigList.size());
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                PROCESS_CONFIG_ID subProcessConfigId = processConfigGraph.getId();
                if (subProcessConfigId == null) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.NO_PROCESS_ID,
                                                       processConfig,
                                                       processConfigGraph,
                                                       processContext,
                                                       processEngineConfig);
                }
                if (subProcessConfigIdSet.contains(subProcessConfigId)) {
                    throw new SfProcessConfigException(SfProcessConfigExceptionCode.ID_REPEAT,
                                                       processConfig,
                                                       processConfigGraph,
                                                       processContext,
                                                       processEngineConfig);
                } else {
                    subProcessConfigIdSet.add(subProcessConfigId);
                }
            }
        }
    }

}
