package org.example.io.output;

import org.example.model.User;
import org.example.util.CustomCollection;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class ConsoleOutput implements Output {
    public void write(CustomCollection<User> users) {
        if (users.size() == 0) {
            System.out.println("Список пуст :(");
            return;
        }

        System.out.printf("%-20s %-20s %-40s%n", "Name", "Password", "Email");
        System.out.println("------------------------------------------------------");

        StreamSupport.stream(users.spliterator(), false)
                .filter(Objects::nonNull)
                .forEach(user ->
                        System.out.printf("%-20s %-20s %-40s%n",
                                user.getName(),
                                user.getPassword(),
                                user.getEmail()
                        )
                );
    }
}
