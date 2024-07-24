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

    public static <NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
            EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>,
            PROCESS_CONFIG_GRAPH extends SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG>,
            PROCESS_CONFIG extends SfAbstractProcessConfig<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH>,
            NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>
    SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
            NODE_CONFIG, EDGE_CONFIG,
            PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID,
            EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> createGlobalContext(
            PROCESS_CONFIG processConfig,
            SfContextFactory<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
                    NODE_CONFIG, EDGE_CONFIG,
                    PROCESS_CONFIG_GRAPH, PROCESS_CONFIG,
                    NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> contextFactory) {
        SfExecutionGlobalContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> executionGlobalContext = contextFactory.createExecutionGlobalContext();
        SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> mainExecutionProcessContext = contextFactory.createExecutionProcessContext();
        executionGlobalContext.setMainExecutionProcessContext(mainExecutionProcessContext);
        mainExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
        mainExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());

        List<PROCESS_CONFIG_GRAPH> subProcessConfigList = processConfig.getSubProcessConfigList();
        if (CollectionUtils.isNotEmpty(subProcessConfigList)) {
            List<SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID>> subExecutionProcessContextList = new ArrayList<>(
                    subProcessConfigList.size());
            executionGlobalContext.setSubExecutionProcessContextList(subExecutionProcessContextList);
            for (PROCESS_CONFIG_GRAPH processConfigGraph : subProcessConfigList) {
                SfExecutionProcessContext<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID, NODE_CONFIG, EDGE_CONFIG, PROCESS_CONFIG_GRAPH, PROCESS_CONFIG, NODE_EXECUTION_ID, EDGE_EXECUTION_ID, PROCESS_EXECUTION_ID> subExecutionProcessContext = contextFactory.createExecutionProcessContext();
                subExecutionProcessContextList.add(subExecutionProcessContext);

                subExecutionProcessContext.setExecutionInternalContext(contextFactory.createExecutionProcessInternalContext());
                subExecutionProcessContext.setExecutionExternalContext(contextFactory.createExecutionProcessExternalContext());
            }
        }

        return executionGlobalContext;
    }

}
