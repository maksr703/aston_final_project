package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RandomUserGeneratorTest {
    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "5, 5", "100, 100"})
    public void constructor_withPositiveCount_shouldCreateGenerator(int inputCount, int expectedSize) {
        RandomUserGenerator generator = new RandomUserGenerator(inputCount);
        CustomCollection<User> users = generator.generateUsers();

        assertEquals(expectedSize, users.size());
    }

    @Test
    void constructor_withNegativeCount_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RandomUserGenerator(-5);
        });
    }

    @Test
    public void generateUsers_withZeroCount_shouldReturnEmptyCollection() {
        int expectedCount = 0;
        RandomUserGenerator generator = new RandomUserGenerator(expectedCount);
        CustomCollection<User> users = generator.generateUsers();

        assertEquals(expectedCount, users.size());
    }

    @Test
    public void generateUsers_withCount5_shouldReturnExactly5Users() {
        RandomUserGenerator generator = new RandomUserGenerator(5);
        CustomCollection<User> users = generator.generateUsers();

        assertEquals(5, users.size());
    }

    @Test
    public void generateUsers_shouldReturnDifferentObjects() {
        RandomUserGenerator generator = new RandomUserGenerator(10);
        CustomCollection<User> users = generator.generateUsers();
        Set<User> userSet = new HashSet<>();

        for (User user : users){
            userSet.add(user);
        }

        assertEquals(userSet.size(), users.size());
    }

    @Test
    public void generateUsers_shouldGenerateUniqueEmails() {
        RandomUserGenerator generator = new RandomUserGenerator(10);
        CustomCollection<User> users = generator.generateUsers();
        Set<String> emailSet = new HashSet<>();

        for (User user : users){
            String email = user.getEmail();
            emailSet.add(email);
        }

        assertEquals(emailSet.size(), users.size());
    }

    @Test
    public void generateUsers_allUsersShouldHaveValidNames() {
        RandomUserGenerator generator = new RandomUserGenerator(1);
        CustomCollection<User> users = generator.generateUsers();
        User user = users.get(0);
        String name = user.getName();

        assertNotNull(name);
        assertFalse(name.isEmpty(), "User name should not be empty");
    }

    @Test
    public void generateUsers_allUsersShouldHaveValidPasswords() {
        RandomUserGenerator generator = new RandomUserGenerator(1);
        CustomCollection<User> users = generator.generateUsers();
        User user = users.get(0);
        String password = user.getPassword();

        assertNotNull(password);
        assertEquals(password.length(), 6);
        assertTrue(password.chars().allMatch(Character :: isDigit));
    }

    @Test
    public void generateUsers_allUsersShouldHaveValidEmails() {
        RandomUserGenerator generator = new RandomUserGenerator(1);
        CustomCollection<User> users = generator.generateUsers();
        User user = users.get(0);
        String email = user.getEmail();
        String validUserNameFromEmail = email.split("@")[0];

        assertNotNull(email);
        assertTrue(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        assertTrue(validUserNameFromEmail.equals(validUserNameFromEmail.toLowerCase()));
        assertTrue(validUserNameFromEmail.matches("^[a-z0-9]+$"));
    }
}