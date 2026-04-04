package org.example.io.output;

import org.example.model.User;
import org.example.util.CustomCollection;

public interface Output {
    void write(CustomCollection<User> users);
}
