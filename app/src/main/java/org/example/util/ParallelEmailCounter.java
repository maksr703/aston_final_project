package org.example.util;

import java.util.Arrays;

import org.example.model.User;

public class ParallelEmailCounter {
    public static void count(CustomCollection<User> source, String target) {
        long count = Arrays.stream(source.getAll())
            .parallel()
            .filter(user -> user.getEmail().equals(target))
            .count(); 
            
        
        System.out.println(count);
    }
}
