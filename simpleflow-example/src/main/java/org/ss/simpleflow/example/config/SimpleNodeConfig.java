package org.ss.simpleflow.example.config;

import org.ss.simpleflow.core.node.SfAbstractNodeConfig;

public class SimpleNodeConfig extends SfAbstractNodeConfig<Long, Long> {
    private String nodeCode;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
}
