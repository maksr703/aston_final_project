package org.example.util;

import java.util.Arrays;

import org.example.model.User;

public class ParallelEmailCounter {
    public static int count(CustomCollection<User> source, String target) {
        return (int) Arrays.stream(source.getAll())
            .parallel()
            .filter(user -> user.getEmail().equals(target))
            .count(); 
    }
}
