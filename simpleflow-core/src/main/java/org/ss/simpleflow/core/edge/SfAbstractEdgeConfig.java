package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;

public class SfAbstractEdgeConfig<EDGE_ID, NODE_ID> implements SfEdgeConfig<EDGE_ID, NODE_ID> {
    protected EDGE_ID id;
    protected NODE_ID fromNodeId;
    protected NODE_ID toNodeId;
    protected String lineType;
    protected String fromResultKey;
    protected String toParameterKey;
    protected SfEdgeIndexEntry lineIndexEntry;

    @Override
    public EDGE_ID getId() {
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
    public String getEdgeType() {
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
    public SfEdgeIndexEntry getEdgeIndexEntry() {
        return lineIndexEntry;
    }

    public boolean isControlLine() {
        return SfEdgeTypeConstant.isControlLine(lineType);
    }

    public boolean isDataLine() {
        return SfEdgeTypeConstant.isDataLine(lineType);
    }
}
