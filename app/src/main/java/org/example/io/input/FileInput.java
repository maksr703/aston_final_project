package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;

public class FileInput implements Input {
    private final String filePath;


    public FileInput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CustomCollection<User> read() {
        //TODO
        return new CustomArrayList<>();
    }
}
