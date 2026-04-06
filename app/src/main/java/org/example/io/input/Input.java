package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;

public interface Input {
    default CustomCollection<User> read(){
        return new CustomArrayList<>();
    }

    default CustomCollection<User> generateUsers(){
        return new CustomArrayList<>();
    }
}
