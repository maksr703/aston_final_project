package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;
import org.example.util.UserParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class FileInput implements Input {
    private final String filePath;


    public FileInput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CustomCollection<User> read() {
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            throw new RuntimeException("Файл недоступен: " + filePath);
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            return bufferedReader.lines()
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        try {
                            return UserParser.parse(line);
                        } catch (Exception e) {
                            System.out.println("Ошибка преобразования строки : "+line+": "+e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(
                            CustomArrayList::new,
                            CustomArrayList::add,
                            (c1, c2) -> {}

                    );
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: "+ filePath +": "+e.getMessage());
        }
    }
}
