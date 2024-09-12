package org.ss.simpleflow.common;

public class IntList {
    private int[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    public IntList() {
        elements = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements[index];
    }

    public int set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int oldValue = elements[index];
        elements[index] = value;
        return oldValue;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        int removedValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

}
