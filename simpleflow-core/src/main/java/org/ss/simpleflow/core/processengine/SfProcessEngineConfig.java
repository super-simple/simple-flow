package org.ss.simpleflow.core.processengine;

import java.io.Serializable;

public class SfProcessEngineConfig implements Serializable {
    private long maxLoopCount = 0;
    private boolean executeOnDataEdge = false;

    public long getMaxLoopCount() {
        return maxLoopCount;
    }

    public void setMaxLoopCount(long maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
    }

    public boolean isExecuteOnDataEdge() {
        return executeOnDataEdge;
    }

    public void setExecuteOnDataEdge(boolean executeOnDataEdge) {
        this.executeOnDataEdge = executeOnDataEdge;
    }
}
