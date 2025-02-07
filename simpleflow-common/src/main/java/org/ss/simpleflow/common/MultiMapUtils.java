package org.ss.simpleflow.common;

import java.util.*;
import java.util.function.Function;

public class MultiMapUtils {

    public static <K, V> Map<K, List<V>> index(Collection<V> values, Function<? super V, K> keyFunction) {
        if (CollectionUtils.isNullOrEmpty(values)) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> result = new HashMap<>(values.size());
        for (V value : values) {
            K apply = keyFunction.apply(value);
            List<V> vs = result.computeIfAbsent(apply, k -> new ArrayList<>());
            vs.add(value);
        }
        return result;
    }

}
