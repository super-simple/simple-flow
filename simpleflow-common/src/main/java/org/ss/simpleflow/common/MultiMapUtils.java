package org.ss.simpleflow.common;

import java.util.*;
import java.util.function.Function;

public class MultiMapUtils {

    public static <K, V> Map<K, List<V>> index(Collection<V> values, Function<? super V, K> keyFunction) {
        if (CollectionUtils.isEmptyOrNull(values)) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> result = new HashMap<>();
        for (V value : values) {
            K apply = keyFunction.apply(value);
            List<V> vs = result.computeIfAbsent(apply, k -> new ArrayList<>());
            vs.add(value);
        }
        return result;
    }

}
