package org.ss.simpleflow.common;

import java.util.function.Predicate;

public class ArrayUtils {

    public static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    public static <T> T[] collect(T[] originalList, Predicate<T> predicate, int newListSize) {
        Object[] result = new Object[newListSize];
        int i = 0;
        for (T item : originalList) {
            if (predicate.test(item)) {
                result[i++] = item;
            }
        }
        return (T[]) result;
    }

}
