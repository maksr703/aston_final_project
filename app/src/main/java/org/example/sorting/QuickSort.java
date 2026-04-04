package org.example.sorting;

import org.example.model.User;
import org.example.util.CustomCollection;

import java.util.Comparator;

public class QuickSort implements Sorting {
    @Override
    public void sort(CustomCollection<User> users, Comparator<User> comparator) {
        if (users == null || users.size() <= 1) {
            return;
        }

        quickSort(users, 0, users.size() - 1, comparator);
    }

    private void quickSort(CustomCollection<User> arr, int low, int high, Comparator<User> comp) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, comp);

            quickSort(arr, low, pivotIndex - 1, comp);
            quickSort(arr, pivotIndex + 1, high, comp);
        }
    }

    private int partition(CustomCollection<User> arr, int low, int high, Comparator<User> comp) {
        int mid = (low + high) / 2;
        User pivot = arr.get(mid);

        swap(arr, mid, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comp.compare(arr.get(j), pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(CustomCollection<User> arr, int i, int j) {
        if (i == j) return;

        User temp = arr.get(i);

        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
