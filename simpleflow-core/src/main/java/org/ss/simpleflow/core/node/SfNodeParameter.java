package org.ss.simpleflow.core.node;

public class SfNodeParameter implements SfNodeIO {

    private boolean notNull = true;

    @Override
    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
