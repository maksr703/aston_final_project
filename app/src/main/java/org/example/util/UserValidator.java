package org.example.util;

public class UserValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private UserValidator() {}

    public static void validate(String name, String password, String email) {
        if (name == null || !name.matches("[a-zA-Zа-яА-Я]+")) {
            throw new IllegalArgumentException("Имя должно содержать только буквы");
        }

        if (password == null || !password.matches("\\d+")) {
            throw new IllegalArgumentException("Пароль может содержать только цифры");
        }

        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Некорректный формат email");
        }
    }
}
