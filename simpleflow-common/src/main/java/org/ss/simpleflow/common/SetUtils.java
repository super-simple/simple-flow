package org.ss.simpleflow.common;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class SetUtils {

    public static <T> Set<T> collect(Set<T> originalList, Predicate<T> predicate) {
        Set<T> result = new HashSet<>();
        for (T item : originalList) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
