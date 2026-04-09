package io.output;

import org.example.io.output.FileOutput;
import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileOutputTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private FileOutput fileOutput;
    private File testFile;
    private String testFilePath;

    @Before
    public void setUp() throws IOException {
        testFile = temporaryFolder.newFile("test_users.txt");
        testFilePath = testFile.getAbsolutePath();
        fileOutput = new FileOutput(testFilePath);

        clearFileContent(testFilePath);
    }

    private void clearFileContent(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        }
    }

    private String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private int countLines(String content) {
        if (content == null || content.isEmpty()) return 0;
        return content.split("\n").length;
    }

    private CustomCollection<User> createTestUsers() {
        CustomCollection<User> users = new CustomArrayList<>();

        User user1 = new User.Builder()
                .setName("Ivan")
                .setPassword("123")
                .setEmail("ivan@google.com")
                .create();

        User user2 = new User.Builder()
                .setName("Sergei")
                .setPassword("456")
                .setEmail("sergei@yandex.ru")
                .create();

        users.add(user1);
        users.add(user2);

        return users;
    }

    @Test
    public void testWrite_ValidUsers_WritesToFile() throws IOException {
        CustomCollection<User> users = createTestUsers();

        fileOutput.write(users);

        assertTrue(testFile.exists());
        String content = readFileContent(testFilePath);

        assertTrue(content.contains("Ivan 123 ivan@google.com"));
        assertTrue(content.contains("Sergei 456 sergei@yandex.ru"));
    }

    @Test
    public void testWrite_EmptyCollection_ThrowsIllegalArgumentException() {
        CustomCollection<User> emptyUsers = new CustomArrayList<>();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Список пользователей не может быть пустым");

        fileOutput.write(emptyUsers);
    }

    @Test
    public void testWrite_AppendsToExistingFile() throws IOException {
        clearFileContent(testFilePath);

        CustomCollection<User> firstList = new CustomArrayList<>();
        User firstUser = new User.Builder()
                .setName("FirstUser")
                .setPassword("1213")
                .setEmail("first@google.com")
                .create();
        firstList.add(firstUser);

        fileOutput.write(firstList);

        String firstContent = readFileContent(testFilePath);
        int firstLineCount = countLines(firstContent);
        assertEquals(1, firstLineCount);

        CustomCollection<User> secondList = new CustomArrayList<>();
        User secondUser = new User.Builder()
                .setName("SecondUser")
                .setPassword("432423")
                .setEmail("second@yandex.ru")
                .create();
        secondList.add(secondUser);

        fileOutput.write(secondList);

        String secondContent = readFileContent(testFilePath);
        int secondLineCount = countLines(secondContent);
        assertEquals(2, secondLineCount);
        assertTrue(secondContent.contains("FirstUser"));
        assertTrue(secondContent.contains("SecondUser"));
    }

    @Test
    public void testWrite_CollectionWithNullElements_SkipsNulls() throws IOException {
        clearFileContent(testFilePath);

        CustomCollection<User> usersWithNull = new CustomArrayList<>();
        User validUser = new User.Builder()
                .setName("ValidUser")
                .setPassword("512523")
                .setEmail("valid@google.com")
                .create();
        usersWithNull.add(validUser);
        usersWithNull.add((User) null);
        usersWithNull.add(validUser);

        fileOutput.write(usersWithNull);

        long lineCount = Files.lines(Paths.get(testFilePath)).count();
        assertEquals(2, lineCount);
    }
}
