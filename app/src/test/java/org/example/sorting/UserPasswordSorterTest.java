package org.example.sorting;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserPasswordSorterTest {

    @Test
    public void sortByEvenPassword() {
        CustomArrayList<User> users = new CustomArrayList<>();

        users.add(new User.Builder()
                .setName("A")
                .setPassword("4")
                .setEmail("a@mail.com")
                .create());

        users.add(new User.Builder()
                .setName("B")
                .setPassword("3")
                .setEmail("b@mail.com")
                .create());

        users.add(new User.Builder()
                .setName("C")
                .setPassword("2")
                .setEmail("c@mail.com")
                .create());

        UserPasswordSorter.sortByEvenPassword(users);

        assertEquals("2", users.get(0).getPassword());
        assertEquals("3", users.get(1).getPassword());
        assertEquals("4", users.get(2).getPassword());
    }
}