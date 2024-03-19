package org.ss.simpleflow.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> List<T> collect(List<T> originalList, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : originalList) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

}
