package org.example.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class CustomArrayList<T> implements CustomCollection<T> {

    private final int DEFAULT_CAPACITY = 15;

    private T[] data;

    private int size;

    public CustomArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T item) {
        ensureCapacity();
        data[size++] = item;
    }

    public void add(CustomCollection<T> customCollection) {
        for (T item : customCollection) {
            add(item);
        }
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            T[] newData = (T[]) new Object[data.length * 2];

            System.arraycopy(data, 0, newData, 0, data.length);

            data = newData;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int i, T user) {
        data[i] = user;
    }

    @Override
    public T[] getAll() {
        T[] result = (T[]) new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return data[cursor++];
            }
        };
    }

    @Override
    public Spliterator<T> spliterator() {
        return new Spliterators.AbstractSpliterator<T>(
                size,
                Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED
        ) {
            private int index = 0;
            private final int border = size;

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                if (index < border) {
                    action.accept(data[index++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<T> trySplit() {
                int low = index;
                int high = border;

                int mid = (low + high) / 2;

                if (low >= mid) {
                    return null;
                }

                index = mid;

                return new Spliterators.AbstractSpliterator<T>(
                        mid - low,
                        Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED
                ) {
                    private int i = low;

                    @Override
                    public boolean tryAdvance(Consumer<? super T> action) {
                        if (i < mid) {
                            action.accept(data[i++]);
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public long estimateSize() {
                        return mid - i;
                    }
                };
            }

            @Override
            public long estimateSize() {
                return border - index;
            }
        };
    }
}
