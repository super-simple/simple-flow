package org.ss.simpleflow.core.processconfig;

import org.ss.simpleflow.core.line.SfAbstractLineConfig;
import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

import java.io.Serializable;
import java.util.List;

public interface SfProcessConfigGraph<NODE_ID, LINE_ID, PROCESS_CONFIG_ID,
        NODE_CONFIG extends SfAbstractNodeConfig<NODE_ID, PROCESS_CONFIG_ID>,
        LINE_CONFIG extends SfAbstractLineConfig<LINE_ID, NODE_ID>> extends Serializable {

    PROCESS_CONFIG_ID getId();

    List<NODE_CONFIG> getNodeConfigList();

    List<LINE_CONFIG> getLineConfigList();
}
