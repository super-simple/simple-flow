package org.ss.simpleflow.core.constant;

public interface SfBuiltInEventCodeConstant {

    String START = "START";

    String END = "END";

    String ERROR = "ERROR";

    String BREAK = "BREAK";

    static boolean isStart(String eventCode) {
        return START.equalsIgnoreCase(eventCode);
    }
}
