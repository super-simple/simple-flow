package org.ss.simpleflow.core.index;

import org.ss.simpleflow.core.constant.SfComponentIndexTypeConstant;
import org.ss.simpleflow.core.constant.SfEdgeIndexTypeConstant;

public class SfIndexEntry {
    private int indexType;
    private int selfIndex;
    private int edgeTypeIndex;
    private int fromNodeConfigIndex;
    private int toNodeConfigIndex;

    public int getIndexType() {
        return indexType;
    }

    public void setIndexTypeNode() {
        this.indexType = SfComponentIndexTypeConstant.INDEX_TYPE_NODE;
    }

    public void setIndexTypeEdge() {
        this.indexType = SfComponentIndexTypeConstant.INDEX_TYPE_EDGE;
    }

    public int getSelfIndex() {
        return selfIndex;
    }

    public void setSelfIndex(int selfIndex) {
        this.selfIndex = selfIndex;
    }

    public int getEdgeTypeIndex() {
        return edgeTypeIndex;
    }

    public void setEdgeTypeIndexControl() {
        this.edgeTypeIndex = SfEdgeIndexTypeConstant.INDEX_TYPE_CONTROL;
    }

    public void setEdgeTypeIndexData() {
        this.edgeTypeIndex = SfEdgeIndexTypeConstant.INDEX_TYPE_DATA;
    }

    public int getFromNodeConfigIndex() {
        return fromNodeConfigIndex;
    }

    public void setFromNodeConfigIndex(int fromNodeConfigIndex) {
        this.fromNodeConfigIndex = fromNodeConfigIndex;
    }

    public int getToNodeConfigIndex() {
        return toNodeConfigIndex;
    }

    public void setToNodeConfigIndex(int toNodeConfigIndex) {
        this.toNodeConfigIndex = toNodeConfigIndex;
    }

    public boolean isControlEdge() {
        return edgeTypeIndex == SfEdgeIndexTypeConstant.INDEX_TYPE_CONTROL;
    }

    public boolean isDataEdge() {
        return edgeTypeIndex == SfEdgeIndexTypeConstant.INDEX_TYPE_DATA;
    }

    public boolean isNode() {
        return indexType == SfComponentIndexTypeConstant.INDEX_TYPE_NODE;
    }

    public boolean isEdge() {
        return indexType == SfComponentIndexTypeConstant.INDEX_TYPE_EDGE;
    }
}
