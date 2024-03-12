package org.ss.simpleflow.core;

import java.util.Set;

public class SimpleFlowPreprocessNodeData {
    private int circleCount;

    private Set<String> parentSet;

    public int getCircleCount() {
        return circleCount;
    }

    public void setCircleCount(int circleCount) {
        this.circleCount = circleCount;
    }

    public Set<String> getParentSet() {
        return parentSet;
    }

    public void setParentSet(Set<String> parentSet) {
        this.parentSet = parentSet;
    }
}
