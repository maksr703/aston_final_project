package org.example.util;

public interface CustomCollection<T> extends Iterable<T> {
    void add(T item);
    void add(CustomCollection<T> customCollection);
    T get(int index);
    int size();

    void set(int i, T user);

    T[] getAll();
}
