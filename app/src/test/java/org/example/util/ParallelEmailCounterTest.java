package org.example.util;

import org.example.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParallelEmailCounterTest {

    @Test
    public void count() {
        CustomArrayList<User> users = new CustomArrayList<>();

        users.add(new User.Builder()
                .setName("Ivan")
                .setPassword("123")
                .setEmail("ivan@gmail.com")
                .create());

        users.add(new User.Builder()
                .setName("Petr")
                .setPassword("123")
                .setEmail("petr@gmail.com")
                .create());

        users.add(new User.Builder()
                .setName("Petr")
                .setPassword("123")
                .setEmail("petr@ya.com")
                .create());

        int result = ParallelEmailCounter.count(users, "@gmail.com");

        assertEquals(2, result);
    }
}
