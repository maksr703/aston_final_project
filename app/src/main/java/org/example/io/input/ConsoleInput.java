package org.example.io.input;

import org.example.model.User;
import org.example.util.CustomArrayList;
import org.example.util.CustomCollection;
import org.example.util.UserParser;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class ConsoleInput implements Input {
    private final int countElements;

    public ConsoleInput(int countElements) {
        this.countElements = countElements;
    }

    @Override
    public CustomCollection<User> read() {
        Scanner sc = new Scanner(System.in);
        return Stream.generate(sc::nextLine)
                .limit(countElements)
                .map(line -> {
                    try {
                        return UserParser.parse(line);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(
                        CustomArrayList::new,
                        CustomArrayList::add,
                        (c1, c2) -> {}
                );
    }
}
