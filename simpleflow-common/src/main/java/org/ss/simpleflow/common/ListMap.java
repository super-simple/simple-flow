package org.ss.simpleflow.common;

import java.util.List;
import java.util.Set;

public interface ListMap<K, V> {
    void put(K key, V value);

    V getByIndex(int index);

    V getByKey(K key);

    V removeByIndex(int index);

    V removeByKey(K key);

    List<V> values();

    List<Entry<K, V>> entryList();

    Set<K> keySet();

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }

}
