package org.example.util;

import org.example.model.User;

public class UserParser {

    private UserParser() {}

    public static User parse(String s) {
        String[] split = s.split(" ");

        if (split.length < 3) {
            throw new IllegalArgumentException("Неверный формат строки. Ожидается: name password email");
        }

        String name = split[0];
        String password = split[1];
        String email = split[2];

        UserValidator.validate(name, password, email);

        return new User.Builder()
                .setName(name)
                .setPassword(password)
                .setEmail(email)
                .create();
    }
}
