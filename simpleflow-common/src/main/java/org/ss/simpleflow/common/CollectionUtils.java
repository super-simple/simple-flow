package org.ss.simpleflow.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class CollectionUtils {

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static <T> List<T> collect(Collection<T> originalList, Predicate<T> predicate, int newListSize) {
        List<T> result = new ArrayList<>(newListSize);
        for (T item : originalList) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> List<T> collect(T[] originalList, Predicate<T> predicate, int newListSize) {
        List<T> result = new ArrayList<>(newListSize);
        for (T item : originalList) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> T find(Collection<T> data, Predicate<T> predicate) {
        if (isNullOrEmpty(data)) {
            return null;
        }
        for (T item : data) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }


}
