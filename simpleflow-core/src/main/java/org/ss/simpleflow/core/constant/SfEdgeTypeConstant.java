package org.ss.simpleflow.core.constant;

public interface SfEdgeTypeConstant {
    String CONTROL = "CONTROL";

    String DATA = "DATA";


    static boolean isControlLine(String lineType) {
        return CONTROL.equalsIgnoreCase(lineType);
    }

    static boolean isDataLine(String lineType) {
        return DATA.equalsIgnoreCase(lineType);
    }
}
