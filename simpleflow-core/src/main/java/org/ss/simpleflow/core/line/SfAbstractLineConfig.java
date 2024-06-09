package org.ss.simpleflow.core.line;

public class SfAbstractLineConfig<LINE_ID, NODE_ID> implements SfLineConfig<LINE_ID, NODE_ID> {
    protected LINE_ID id;
    protected NODE_ID fromNodeId;
    protected NODE_ID toNodeId;
    protected String lineType;
    protected String fromResultKey;
    protected String toParameterKey;
    protected SfLineIndexEntry lineIndexEntry;

    @Override
    public LINE_ID getId() {
        return id;
    }

    @Override
    public NODE_ID getFromNodeId() {
        return fromNodeId;
    }

    @Override
    public NODE_ID getToNodeId() {
        return toNodeId;
    }

    @Override
    public String getLineType() {
        return lineType;
    }

    @Override
    public String getFromResultKey() {
        return fromResultKey;
    }

    @Override
    public String getToParameterKey() {
        return toParameterKey;
    }

    @Override
    public SfLineIndexEntry getLineIndexEntry() {
        return lineIndexEntry;
    }
}
