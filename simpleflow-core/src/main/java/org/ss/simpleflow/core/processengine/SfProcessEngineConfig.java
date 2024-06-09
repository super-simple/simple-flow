package org.ss.simpleflow.core.processengine;

import java.io.Serializable;

public class SfProcessEngineConfig implements Serializable {

    private boolean trim;
    private long maxLoopCount = 0;

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    public long getMaxLoopCount() {
        return maxLoopCount;
    }

    public void setMaxLoopCount(long maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
    }
}
