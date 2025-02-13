package org.ss.simpleflow.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListHashMapTest {

    private ArrayListHashMap<String, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new ArrayListHashMap<>();
    }

    @Test
    public void testPutAndGetByKey() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.getByKey("one"));
        assertEquals(2, map.getByKey("two"));
        assertEquals(3, map.getByKey("three"));
        assertNull(map.getByKey("four"));
    }

    @Test
    public void testPutAndGetByIndex() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.getByIndex(0));
        assertEquals(2, map.getByIndex(1));
        assertEquals(3, map.getByIndex(2));
    }

    @Test
    public void testPutDuplicateKey() {
        map.put("one", 1);
        map.put("one", 10);

        assertEquals(10, map.getByKey("one"));
        assertEquals(1, map.size());
    }

    @Test
    public void testRemoveByKey() {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.removeByKey("B"); // 删除后，"C"的索引应从2变为1
        System.out.println(map.getByKey("C")); // 应正确输出3
    }

    @Test
    public void testRemoveByIndex() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(2, map.removeByIndex(1));
        assertNull(map.getByKey("two"));
        assertEquals(2, map.size());
    }

    @Test
    public void testValues() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        List<Integer> values = map.values();
        assertEquals(3, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
    }

    @Test
    public void testEntryList() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        List<ListMap.Entry<String, Integer>> entries = map.entryList();
        assertEquals(3, entries.size());
        assertEquals("one", entries.get(0).getKey());
        assertEquals(1, entries.get(0).getValue());
        assertEquals("two", entries.get(1).getKey());
        assertEquals(2, entries.get(1).getValue());
        assertEquals("three", entries.get(2).getKey());
        assertEquals(3, entries.get(2).getValue());
    }

    @Test
    public void testKeySet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Set<String> keys = map.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("one"));
        assertTrue(keys.contains("two"));
        assertTrue(keys.contains("three"));
    }

    @Test
    public void testSize() {
        assertEquals(0, map.size());

        map.put("one", 1);
        assertEquals(1, map.size());

        map.put("two", 2);
        assertEquals(2, map.size());

        map.removeByKey("one");
        assertEquals(1, map.size());
    }

    @Test
    public void testNullKey() {
        assertThrows(NullPointerException.class, () -> map.put(null, 1));
    }

    @Test
    public void testRemoveNonExistentKey() {
        map.put("one", 1);
        assertNull(map.removeByKey("two"));
    }

    @Test
    public void testRemoveNonExistentIndex() {
        map.put("one", 1);
        assertThrows(IndexOutOfBoundsException.class, () -> map.removeByIndex(1));
    }

    @Test
    public void testGetByNonExistentIndex() {
        map.put("one", 1);
        assertThrows(IndexOutOfBoundsException.class, () -> map.getByIndex(1));
    }
}