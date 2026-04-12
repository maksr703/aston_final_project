package org.example.sorting;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class QuickSortTest {
    @Test
    public void sort_sortsCorrectly() {
        CustomArrayList<User> list = new CustomArrayList<>();

        list.add(new User.Builder().setName("B").setPassword("1").setEmail("b@mail.com").create());
        list.add(new User.Builder().setName("A").setPassword("1").setEmail("a@mail.com").create());

        Sorting sorter = new QuickSort();

        sorter.sort(list, Comparator.comparing(User::getName));

        assertEquals("A", list.get(0).getName());
        assertEquals("B", list.get(1).getName());
    }

    @Test
    public void sort_emptyList() {
        CustomArrayList<User> list = new CustomArrayList<>();

        Sorting sorter = new QuickSort();

        sorter.sort(list, Comparator.comparing(User::getName));
    }
}
