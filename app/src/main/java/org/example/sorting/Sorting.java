package org.example.sorting;

import org.example.model.User;
import org.example.util.CustomCollection;

import java.util.Comparator;

public interface Sorting {
    void sort(CustomCollection<User> users, Comparator<User> comparator);
}
