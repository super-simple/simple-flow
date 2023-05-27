package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowLineConfig;

public class SimpleFlowLineConfigImpl extends SimpleFlowComponentConfigImpl implements SimpleFlowLineConfig {

    private String fromId;

    private String toId;

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @Override
    public String getFromNodeId() {
        return fromId;
    }

    @Override
    public String getToNodeId() {
        return toId;
    }
}
