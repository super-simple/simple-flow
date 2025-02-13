package org.ss.simpleflow.common;

import java.util.*;

public class ArrayListHashMap<K, V> implements ListMap<K, V> {

    private final List<ListMap.Entry<K, V>> entries;
    private final Map<K, Integer> keyToIndex;

    public ArrayListHashMap() {
        this.entries = new ArrayList<>();
        this.keyToIndex = new HashMap<>();
    }

    public ArrayListHashMap(int initSize) {
        this.entries = new ArrayList<>(initSize);
        this.keyToIndex = new HashMap<>(initSize);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null.");
        }
        if (keyToIndex.containsKey(key)) {
            // 替换现有值
            int index = keyToIndex.get(key);
            entries.set(index, new Entry<>(key, value));
        } else {
            entries.add(new Entry<>(key, value));
            keyToIndex.put(key, entries.size() - 1);
        }
    }

    @Override
    public V getByIndex(int index) {
        return entries.get(index).getValue();
    }

    @Override
    public V getByKey(K key) {
        Integer index = keyToIndex.get(key);
        return index != null ? entries.get(index).getValue() : null;
    }

    @Override
    public V removeByIndex(int index) {
        ListMap.Entry<K, V> removedEntry = entries.remove(index);
        keyToIndex.remove(removedEntry.getKey());

        // 修复点：更新后续元素的索引
        for (int i = index; i < entries.size(); i++) {
            keyToIndex.put(entries.get(i).getKey(), i);
        }
        return removedEntry.getValue();
    }

    @Override
    public V removeByKey(K key) {
        Integer index = keyToIndex.remove(key);
        if (index == null) {
            return null;
        }
        ListMap.Entry<K, V> removedEntry = entries.remove(index.intValue());

        // 修复点：更新后续元素的索引
        for (int i = index; i < entries.size(); i++) {
            keyToIndex.put(entries.get(i).getKey(), i);
        }
        return removedEntry.getValue();
    }

    @Override
    public List<V> values() {
        List<V> result = new ArrayList<>(entries.size());
        for (ListMap.Entry<K, V> entry : entries) {
            result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public List<ListMap.Entry<K, V>> entryList() {
        return entries;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(keyToIndex.keySet());
    }

    @Override
    public int size() {
        return entries.size();
    }

    public static class Entry<K, V> implements ListMap.Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}