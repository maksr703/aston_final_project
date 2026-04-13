package org.example.util;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class UserValidatorTest {

    @Test
    public void validate_validData() {
        UserValidator.validate("Ivan", "12345", "ivan@mail.com");
    }

    @Test
    public void validate_invalidName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> UserValidator.validate("Ivan123", "12345", "ivan@mail.com"));
    }

    @Test
    public void validate_invalidPassword_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> UserValidator.validate("Ivan", "pass", "ivan@mail.com"));
    }

    @Test
    public void validate_invalidEmail_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> UserValidator.validate("Ivan", "12345", "invalid"));
    }
}
