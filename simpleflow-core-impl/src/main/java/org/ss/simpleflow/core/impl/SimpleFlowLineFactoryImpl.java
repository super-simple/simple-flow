package org.ss.simpleflow.core.impl;

import org.ss.simpleflow.core.SimpleFlowLine;
import org.ss.simpleflow.core.SimpleFlowLineFactory;

public class SimpleFlowLineFactoryImpl implements SimpleFlowLineFactory {
    @Override
    public SimpleFlowLine getLine(String processId, String lineId) {
        return new SimpleFlowAbstractTrueLineImpl();
    }
}
