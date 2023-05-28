package org.ss.simpleflow.core.line;

import org.ss.simpleflow.core.component.SimpleFlowAbstractComponentConfig;

public abstract class SimpleFlowAbstractAbstractLineConfig extends SimpleFlowAbstractComponentConfig implements SimpleFlowLineConfig {

    protected String fromId;

    protected String toId;

    void setFromId(String fromId) {
        this.fromId = fromId;
    }

    void setToId(String toId) {
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
