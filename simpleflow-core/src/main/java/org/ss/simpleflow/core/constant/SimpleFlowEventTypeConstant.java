package org.ss.simpleflow.core.constant;

public interface SimpleFlowEventTypeConstant {
    String CATCH = "CATCH";
    String THROW = "THROW";

    static boolean isCatch(String eventType) {
        return CATCH.equalsIgnoreCase(eventType);
    }

    static boolean isThrow(String eventType) {
        return THROW.equalsIgnoreCase(eventType);
    }

    static boolean isLegal(String eventType) {
        return CATCH.equalsIgnoreCase(eventType) || THROW.equalsIgnoreCase(eventType);
    }
}
