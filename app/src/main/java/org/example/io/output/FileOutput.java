package org.example.io.output;

import org.example.model.User;
import org.example.util.CustomCollection;

public class FileOutput implements Output {
    private final String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(CustomCollection<User> users) {
        //TODO
    }
}
