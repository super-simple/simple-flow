package org.ss.simpleflow.common;

public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.length() == 0;
    }

}
