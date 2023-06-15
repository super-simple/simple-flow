package org.ss.simpleflow.core;

import java.util.Set;

public class NodeContext {

    private Set<String> fromNodeSet;
    private int toCount;
    private int circleCount;

    public Set<String> getFromNodeSet() {
        return fromNodeSet;
    }

    public void setFromNodeSet(Set<String> fromNodeSet) {
        this.fromNodeSet = fromNodeSet;
    }

    public int getToCount() {
        return toCount;
    }

    public void setToCount(int toCount) {
        this.toCount = toCount;
    }

    public int getCircleCount() {
        return circleCount;
    }

    public void setCircleCount(int circleCount) {
        this.circleCount = circleCount;
    }
}
