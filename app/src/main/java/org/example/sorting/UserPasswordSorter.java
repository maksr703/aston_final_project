package org.example.sorting;

import org.example.model.User;
import org.example.util.CustomCollection;

import java.util.*;

public class UserPasswordSorter {
    public static void sortByEvenPassword(CustomCollection<User> users) {
        List<Map.Entry<Integer, User>> evenPasswordUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isPasswordEven()) {
                evenPasswordUsers.add(new AbstractMap.SimpleEntry<>(i, users.get(i)));
            }
        }
        evenPasswordUsers.sort(Comparator.comparing(e -> {
            try {
                return Integer.parseInt(e.getValue().getPassword());
            } catch (NumberFormatException ex) {
                System.err.println("Ошибка преобразования пароля: " + e.getValue().getPassword());
                return 0;
            }
        }));
        Iterator<Map.Entry<Integer, User>> evenIterator = evenPasswordUsers.iterator();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isPasswordEven() && evenIterator.hasNext()) {
                users.set(i, evenIterator.next().getValue());
            }
        }
    }
}
