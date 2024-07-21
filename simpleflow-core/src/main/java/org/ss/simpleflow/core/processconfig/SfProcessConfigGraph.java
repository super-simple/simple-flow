package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;
import java.util.List;

public interface SfProcessConfigGraph<NODE_ID, EDGE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        EDGE_CONFIG extends SfAbstractEdgeConfig<EDGE_ID, NODE_ID>> extends Serializable {

    PROCESS_CONFIG_ID getId();

    List<NODE_CONFIG> getNodeConfigList();

    List<EDGE_CONFIG> getLineConfigList();
}
