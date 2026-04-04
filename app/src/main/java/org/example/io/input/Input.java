package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomCollection;

public interface Input {
    CustomCollection<User> read();
}
