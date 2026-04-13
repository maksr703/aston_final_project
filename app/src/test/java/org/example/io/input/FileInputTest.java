package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class FileInputTest {

    private FileInput fileInput;
    private Path tempDir;
    private Path testFile;
    private Path nonExistentFile;

    @Before
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("junit-temp");
        testFile = tempDir.resolve("users.txt");
        nonExistentFile = tempDir.resolve("not_exist_txt");
    }

    @After
    public void tearDown() throws IOException {
        if (tempDir != null && Files.exists(tempDir)) {
            Files.walk(tempDir)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    public void read_ShouldFilterEmptyLines() throws IOException {
        List<String> lines = List.of(
                "ivan 21551 ivan@mail.ru",
                "",
                "   ",
                "petr 663444 petr@mail.ru",
                ""
        );
        Files.write(testFile, lines);
        fileInput = new FileInput(testFile.toString());

        CustomCollection<User> result = fileInput.read();

        assertEquals(2, result.size());
    }

    @Test
    public void read_ShouldReturnCustomCollection_WhenFileExistsAndValid() throws IOException {
        List<String> lines = List.of(
                "alex 123 alex@example.com",
                "maria 456 maria@example.com",
                "john 789 john@gmail.com"
        );
        Files.write(testFile, lines);
        fileInput = new FileInput(testFile.toString());

        CustomCollection<User> result = fileInput.read();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void read_ShouldSkipInvalidLines() throws IOException {
        // given
        List<String> lines = List.of(
                "alex 123 alex@example.com",
                "maria 456 maria@example.com",
                "invalid_line_without_commas",
                "anna 214 invalid_email"
        );
        Files.write(testFile, lines);
        fileInput = new FileInput(testFile.toString());

        CustomCollection<User> result = fileInput.read();

        assertEquals(2, result.size());
    }

    @Test
    public void read_ShouldThrowException_WhenFileDoesNotExist() {
        fileInput = new FileInput(nonExistentFile.toString());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fileInput.read();
        });

        assertTrue(exception.getMessage().contains("Файл недоступен"));
        assertTrue(exception.getMessage().contains(nonExistentFile.toString()));
    }

    @Test
    public void read_ShouldReturnEmptyCollection_WhenFileIsEmpty() throws IOException {
        Files.createFile(testFile);
        fileInput = new FileInput(testFile.toString());

        CustomCollection<User> result = fileInput.read();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test(expected = RuntimeException.class)
    public void read_ShouldThrowIOException_WhenReadingFails() {
        String invalidPath = "./doesnotexistfile.txt";
        fileInput = new FileInput(invalidPath);
        fileInput.read();
    }
}
