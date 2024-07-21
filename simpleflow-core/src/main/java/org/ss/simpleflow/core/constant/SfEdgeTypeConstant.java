package org.ss.simpleflow.core.constant;

public interface SfEdgeTypeConstant {
    String CONTROL = "CONTROL";

    String DATA = "DATA";

    static boolean isControlEdge(String edgeType) {
        return CONTROL.equalsIgnoreCase(edgeType);
    }

    static boolean isDataEdge(String edgeType) {
        return DATA.equalsIgnoreCase(edgeType);
    }
}
