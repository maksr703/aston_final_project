package org.example.sorting;

import java.util.Comparator;
import java.util.Objects;

public class EmailComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (Objects.equals(o1, o2)) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;

        int at1 = o1.indexOf('@');
        int at2 = o2.indexOf('@');

        if (at1 == -1 || at2 == -1) {
            return o1.compareTo(o2);
        }

        int result = o1.substring(at1 + 1).compareTo(o2.substring(at2 + 1));
        if (result != 0) {
            return result;
        }

        return o1.substring(0, at1).compareTo(o2.substring(0, at2));
    }
}

