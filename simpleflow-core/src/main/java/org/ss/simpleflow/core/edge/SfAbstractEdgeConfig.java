package org.ss.simpleflow.core.edge;

import org.ss.simpleflow.core.constant.SfEdgeTypeConstant;

public class SfAbstractEdgeConfig<EI, NI> implements SfEdgeConfig<EI, NI> {
    protected EI id;
    protected NI fromNodeId;
    protected NI toNodeId;
    protected String edgeType;
    protected int executePriority;
    protected int fromResultIndex;
    protected int toParameterIndex;

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
    public int getFromResultIndex() {
        return fromResultIndex;
    }

    @Override
    public int getExecutePriority() {
        return executePriority;
    }

    @Override
    public int getToParameterIndex() {
        return toParameterIndex;
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

    public void setExecutePriority(int executePriority) {
        this.executePriority = executePriority;
    }

    public void setFromResultIndex(int fromResultIndex) {
        this.fromResultIndex = fromResultIndex;
    }

    public void setToParameterIndex(int toParameterIndex) {
        this.toParameterIndex = toParameterIndex;
    }

    public boolean isControlEdge() {
        return SfEdgeTypeConstant.isControlEdge(edgeType);
    }

    public boolean isDataEdge() {
        return SfEdgeTypeConstant.isDataEdge(edgeType);
    }

    public int fetchEdgeTypeIndex() {
        return SfEdgeTypeConstant.isControlEdge(edgeType) ? 0 : 1;
    }
}
