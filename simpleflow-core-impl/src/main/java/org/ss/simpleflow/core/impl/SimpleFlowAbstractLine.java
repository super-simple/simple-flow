package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.line.SimpleFlowAbstractLineConfig;
import org.ss.simpleflow.core.line.SimpleFlowLine;

public abstract class SimpleFlowAbstractLine extends SimpleFlowAbstractComponent implements SimpleFlowLine {

    private SimpleFlowAbstractLineConfig config;


    void setConfig(SimpleFlowAbstractLineConfig config) {
        this.config = config;
    }

    @Override
    public SimpleFlowAbstractLineConfig getConfig() {
        return config;
    }
}
