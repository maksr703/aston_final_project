package org.example.util;

import org.example.model.User;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class ParallelEmailCounter {
    public static int count(CustomCollection<User> source, String target) {
        return (int) StreamSupport.stream(source.spliterator(), true)
                .filter(Objects::nonNull)
                .filter(user -> user.getEmail().endsWith(target))
                .count();
    }
}
