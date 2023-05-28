package org.ss.simpleflow.core.impl.simple.line;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractLine;
import org.ss.simpleflow.core.impl.SimpleFlowLineFactory;
import org.ss.simpleflow.core.line.SimpleFlowLineConfig;

public class SimpleFlowSimpleLineFactory implements SimpleFlowLineFactory {
    @Override
    public SimpleFlowAbstractLine getLine(String processId, String lineId, String code, SimpleFlowLineConfig lineConfig) {
        return new SimpleFlowSimpleLine();
    }
}
