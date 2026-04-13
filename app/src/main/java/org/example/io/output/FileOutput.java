package org.example.io.output;

import org.example.model.User;
import org.example.util.CustomCollection;

import java.io.*;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class FileOutput implements Output {
    private final String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(CustomCollection<User> users) {
        if (users.size() < 1) {
            throw new IllegalArgumentException("Список пользователей не может быть пустым");
        }
        File file = new File(filePath);

        try{
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка создания файла: ", e);
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            StreamSupport.stream(users.spliterator(), false)
                    .filter(Objects::nonNull)
                    .forEach(user -> {
                        try{
                            bufferedWriter.write(user.toString());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException("Ошибка записи в файл: ",e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл: ",e);
        }
    }
}
