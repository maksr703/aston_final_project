package org.example.util;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class CustomArrayListTest {
    @Test
    public void add_and_size() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
    }

    @Test
    public void get_validIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);

        assertEquals(1, (int) list.get(0));
    }

    @Test
    public void get_invalidIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));
    }

    @Test
    public void set() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);

        list.set(0, 2);

        assertEquals(2, (int) list.get(0));
    }

    @Test
    public void ensureCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>(); // DEFAULT_CAPACITY = 15

        for (int i = 0; i < 25; i++) {
            list.add(i);
        }

        assertEquals(25, list.size());
    }

    @Test
    public void iterator() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> it = list.iterator();

        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next());
        assertEquals(2, (int) it.next());
        assertFalse(it.hasNext());
    }
}
