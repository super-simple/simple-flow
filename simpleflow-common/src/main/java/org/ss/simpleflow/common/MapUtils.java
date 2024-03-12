package org.ss.simpleflow.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MapUtils {

    public static <K, V> Map<K, V> uniqueIndex(Collection<V> values, Function<? super V, K> keyFunction) {
        if (CollectionUtils.isNullOrEmpty(values)) {
            return Collections.emptyMap();
        }
        Map<K, V> result = new HashMap<>();
        for (V value : values) {
            result.put(keyFunction.apply(value), value);
        }
        return result;
    }

}
