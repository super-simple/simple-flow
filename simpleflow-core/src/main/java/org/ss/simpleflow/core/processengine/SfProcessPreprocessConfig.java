package org.ss.simpleflow.core.processengine;

import java.io.Serializable;

public class SfProcessPreprocessConfig implements Serializable {

    private boolean cleanOrphanNode = true;

    public boolean isCleanOrphanNode() {
        return cleanOrphanNode;
    }

    public void setCleanOrphanNode(boolean cleanOrphanNode) {
        this.cleanOrphanNode = cleanOrphanNode;
    }

}
