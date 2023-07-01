package org.ss.simpleflow.common;

import java.util.Collection;

public class CollectionUtils {

    public static <T> boolean isEmptyOrNull(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

}
