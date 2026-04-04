package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;

public class RandomInput implements Input {
    private final int countElements;

    public RandomInput(int countElements) {
        this.countElements = countElements;
    }

    @Override
    public CustomCollection<User> read() {
        //TODO
        return new CustomArrayList<>();
    }
}
