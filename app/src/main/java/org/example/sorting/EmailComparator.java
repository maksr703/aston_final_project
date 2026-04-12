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

        // Если формат некорректный — сравниваем как строки
        if (at1 == -1 || at2 == -1) {
            return o1.compareTo(o2);
        }

        String local1 = o1.substring(0, at1);
        String domain1 = o1.substring(at1 + 1);

        String local2 = o2.substring(0, at2);
        String domain2 = o2.substring(at2 + 1);

        // Сначала сравниваем домены
        int result = domain1.compareTo(domain2);
        if (result != 0) {
            return result;
        }

        // Если домены равны — сравниваем local-part
        return local1.compareTo(local2);
    }
}
