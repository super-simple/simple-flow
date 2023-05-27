package org.ss.simpleflow.core;

public interface SimpleFlowLineFactory {

    SimpleFlowLine getLine(String processId, String lineId);

}
