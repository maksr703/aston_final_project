package org.example;

import org.example.service.UserService;
import org.example.ui.UI;

public class App {
    public static void main(String[] args) {
        UserService userService = UserService.createDefault();
        UI ui = new UI(userService);
        ui.run();

    };
}
