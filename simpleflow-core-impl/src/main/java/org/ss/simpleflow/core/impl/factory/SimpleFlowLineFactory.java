package org.ss.simpleflow.core.impl.factory;

import org.ss.simpleflow.core.impl.SimpleFlowAbstractLine;
import org.ss.simpleflow.core.line.SimpleFlowLineConfig;

public interface SimpleFlowLineFactory {

    SimpleFlowAbstractLine getLine(String processId, String lineId, String code, SimpleFlowLineConfig lineConfig);

}
