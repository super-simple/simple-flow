package org.ss.simpleflow.core.processengine;

import java.io.Serializable;

public class SfProcessEngineConfig implements Serializable {

    private boolean cleanOrphanNode = true;
    private long maxLoopCount = 0;

    public boolean isCleanOrphanNode() {
        return cleanOrphanNode;
    }

    public void setCleanOrphanNode(boolean cleanOrphanNode) {
        this.cleanOrphanNode = cleanOrphanNode;
    }

    public long getMaxLoopCount() {
        return maxLoopCount;
    }

    public void setMaxLoopCount(long maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
    }
}
