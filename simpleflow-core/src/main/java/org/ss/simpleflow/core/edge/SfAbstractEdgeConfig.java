package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;

public class SfAbstractEdgeConfig<EI, NI> implements SfEdgeConfig<EI, NI> {
    protected EI id;
    protected NI fromNodeId;
    protected NI toNodeId;
    protected String edgeType;
    protected String fromResultKey;
    protected String toParameterKey;
    private SfEdgeIndexEntry edgeIndexEntry;

    @Override
    public EI getId() {
        return id;
    }

    @Override
    public NI getFromNodeId() {
        return fromNodeId;
    }

    @Override
    public NI getToNodeId() {
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

    public void setId(EI id) {
        this.id = id;
    }

    public void setFromNodeId(NI fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public void setToNodeId(NI toNodeId) {
        this.toNodeId = toNodeId;
    }

    public void setEdgeType(String edgeType) {
        this.edgeType = edgeType;
    }

    public void setFromResultKey(String fromResultKey) {
        this.fromResultKey = fromResultKey;
    }

    public void setToParameterKey(String toParameterKey) {
        this.toParameterKey = toParameterKey;
    }

    public SfEdgeIndexEntry getEdgeIndexEntry() {
        return edgeIndexEntry;
    }

    public void setEdgeIndexEntry(SfEdgeIndexEntry edgeIndexEntry) {
        this.edgeIndexEntry = edgeIndexEntry;
    }

    public boolean isControlEdge() {
        return SfEdgeTypeConstant.isControlEdge(edgeType);
    }

    public boolean isDataEdge() {
        return SfEdgeTypeConstant.isDataEdge(edgeType);
    }
}
