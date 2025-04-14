package org.ss.simpleflow.example.config;

import org.ss.simpleflow.core.edge.SfAbstractEdgeConfig;

public class SimpleEdgeConfig extends SfAbstractEdgeConfig<Long, Long> {

    private String edgeCode;

    public String getEdgeCode() {
        return edgeCode;
    }

    public void setEdgeCode(String edgeCode) {
        this.edgeCode = edgeCode;
    }

}
