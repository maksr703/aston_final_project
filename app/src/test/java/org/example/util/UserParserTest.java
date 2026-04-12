package org.example.util;

import org.example.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UserParserTest {
    @Test
    public void parse_validInput() {
        User user = UserParser.parse("Ivan 12345 ivan@mail.com");

        assertEquals("Ivan", user.getName());
        assertEquals("12345", user.getPassword());
        assertEquals("ivan@mail.com", user.getEmail());
    }

    @Test
    public void parse_invalidFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> UserParser.parse("Ivan 12345"));
    }

    @Test
    public void parse_invalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> UserParser.parse("Ivan 12345 invalid_email"));
    }
}
