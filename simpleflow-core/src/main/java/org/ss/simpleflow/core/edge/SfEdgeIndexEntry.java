package org.ss.simpleflow.core.edge;

public class SfEdgeIndexEntry {

    private int edgeTypeIndex;
    private int edgeIndex;
    private int fromNodeConfigIndex;
    private int toNodeConfigIndex;

    public int getEdgeTypeIndex() {
        return edgeTypeIndex;
    }

    public void setEdgeTypeIndex(int edgeTypeIndex) {
        this.edgeTypeIndex = edgeTypeIndex;
    }

    public int getEdgeIndex() {
        return edgeIndex;
    }

    public void setEdgeIndex(int edgeIndex) {
        this.edgeIndex = edgeIndex;
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
}
