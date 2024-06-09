package org.ss.simpleflow.core.node;

public class SfNodeResult implements SfNodeIO {

    private boolean notNull = true;

    @Override
    public boolean isNotNull() {
        return false;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
