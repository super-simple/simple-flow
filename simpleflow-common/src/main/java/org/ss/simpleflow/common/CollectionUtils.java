package org.ss.simpleflow.common;

import java.util.Collection;

public class CollectionUtils {

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && collection.size() > 0;
    }
}
