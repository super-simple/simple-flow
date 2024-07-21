package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;

public class SfAbstractEdgeConfig<EDGE_ID, NODE_ID> implements SfEdgeConfig<EDGE_ID, NODE_ID> {
    protected EDGE_ID id;
    protected NODE_ID fromNodeId;
    protected NODE_ID toNodeId;
    protected String edgeType;
    protected String fromResultKey;
    protected String toParameterKey;
    protected SfEdgeIndexEntry edgeIndexEntry;

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
        return edgeType;
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
        return edgeIndexEntry;
    }

    public boolean isControlEdge() {
        return SfEdgeTypeConstant.isControlEdge(edgeType);
    }

    public boolean isDataEdge() {
        return SfEdgeTypeConstant.isDataEdge(edgeType);
    }
}
