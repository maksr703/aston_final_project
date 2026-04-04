package org.example.sorting;

import org.example.model.User;

import java.util.Comparator;

public class UserComparators {

    private UserComparators() {
    }

    public static Comparator<User> byName() {
        return Comparator.comparing(User::getName);
    }

    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail, new EmailComparator());
    }

    public static Comparator<User> byPassword() {
        return Comparator.comparing(User::getPassword);
    }
}
