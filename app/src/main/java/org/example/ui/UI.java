package org.example.ui;

import org.example.io.input.ConsoleInput;
import org.example.io.input.FileInput;
import org.example.io.input.Input;
import org.example.io.input.RandomInput;
import org.example.io.output.ConsoleOutput;
import org.example.io.output.FileOutput;
import org.example.service.UserService;

import java.util.Scanner;

public class UI {
    Scanner sc = new Scanner(System.in);

    UserService userService;

    public UI(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        boolean running = true;

        while(running) {
            clearConsole();
            printMainMenu();
            int choice = readInt();
            running = handleMainMenu(choice);
        }

    }

    private boolean handleMainMenu(int choice) {
        switch (choice) {
            case 1 -> showCurrentList();
            case 2 -> showInputMenu();
            case 3 -> showSortMenu();
            case 4 -> exportUsers();
            case 5 -> showCountMenu();
            case 0 -> {
                return false;
            }
            default -> {
                System.out.println("Некорректный ввод!");
                sleep(1000);
            }
        }
        return true;
    }

    private void showCountMenu() {
        while (true) {
            clearConsole();

            System.out.println("Введите домен (например: gmail.com)");
            System.out.println("0 — назад");
            System.out.print("\nВаш ввод: ");

            String input = sc.nextLine().trim();

            if (input.equals("0")) {
                return;
            }

            if (input.isEmpty()) {
                System.out.println("Пустой ввод!");
                sleep(1000);
                continue;
            }

            System.out.print("Найдено: ");
            userService.countByEmailDomain(input);

            pause();
        }
    }

    private void exportUsers() {
        clearConsole();

        try {
            userService.outputUsers(new FileOutput("./output.txt"));
            System.out.println("Данные успешно сохранены!");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }

        pause();
    }

    private void showCurrentList() {
        clearConsole();
        
        userService.outputUsers(new ConsoleOutput());

        pause();
    }

    /* Input */
    private void showInputMenu() {
        while(true){
            clearConsole();
            printInputMenu();
            int choice = readInt();

            if (!handleInputMenu(choice)) {
                return;
            }
        }
    }

    private boolean handleInputMenu(int choice) {
        switch (choice) {
            case 1 -> {
                consoleInputMenu();
                return true;
            }
            case 2 -> {
                fileInputMenu();
                return true;
            }
            case 3 -> {
                randomInputMenu();
                return true;
            }
            case 4 -> {
                return  false;
            }
            default -> {
                System.out.println("Некорректный ввод");
                sleep(1000);
                return true;
            }
        }
    }

    private void consoleInputMenu() {
        while (true) {
            System.out.println("Введите кол-во элементов (0 — назад): ");

            if (!sc.hasNextInt()) {
                System.out.println("Ошибка: введите число!");
                sc.next();
                continue;
            }

            int count = sc.nextInt();

            if (count == 0) return;

            if (count < 0) {
                System.out.println("Число должно быть положительным!");
                continue;
            }

            Input input = new ConsoleInput(count);
            userService.addUsers(input.read());

            break;
        }
    }

    private void fileInputMenu() {
        while (true) {
            clearConsole();
            System.out.print("Введите путь к файлу (0 — назад): ");

            String path = sc.nextLine().trim();

            if (path.equals("0")) {
                return;
            }

            if (path.isEmpty()) {
                System.out.println("Путь не может быть пустым!");
                sleep(1000);
                continue;
            }

            try {
                Input input = new FileInput(path);
                userService.addUsers(input.read());

                System.out.println("Данные успешно загружены!");
                pause();
                return;

            } catch (Exception e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
                sleep(2000);
            }
        }
    }

    private void randomInputMenu() {
        while (true) {
            System.out.println("Введите кол-во элементов (0 — назад): ");

            if (!sc.hasNextInt()) {
                System.out.println("Ошибка: введите число!");
                sc.next();
                continue;
            }

            int count = sc.nextInt();

            if (count == 0) return;

            if (count < 0) {
                System.out.println("Число должно быть положительным!");
                continue;
            }

            Input input = new RandomInput(count);
            userService.addUsers(input.read());

            break;
        }
    }

    /* Sorting */
    private void showSortMenu() {
        while(true){
            clearConsole();
            printSortMenu();
            int choice = readInt();

            if (!handleSortMenu(choice)) {
                return;
            }
        }
    }

    private boolean handleSortMenu(int choice) {
        switch (choice) {
            case 1 -> {
                userService.sortByName();
                return true;
            }
            case 2 -> {
                userService.sortByPassword();
                return true;
            }
            case 3 -> {
                userService.sortByEmail();
                return true;
            }
            case 4 -> {
                userService.sortByEvenPassword();
                return  true;
            }
            case 5 -> {
                return  false;
            }
            default -> {
                System.out.println("Некорректный ввод");
                sleep(1000);
                return true;
            }
        }
    }

    /* Print methods */

    private void printMainMenu() {
        System.out.println("1. Показать текущий список");
        System.out.println("2. Ввод данных");
        System.out.println("3. Сортировка списка");
        System.out.println("4. Сохранить список в файл");
        System.out.println("5. Подсчет пользователей по email домену");
        System.out.println("0. Выход");
        System.out.print("\nВыберите действие: ");

    }

    private void printInputMenu() {
        System.out.println("1. Ввести вручную");
        System.out.println("2. Загрузить из файла");
        System.out.println("3. Сгенерировать");
        System.out.println("4. Назад");
        System.out.print("\nВыберите действие: ");
    }

    private void printSortMenu() {
        System.out.println("1. Сортировка по имени");
        System.out.println("2. Сортировка по паролю");
        System.out.println("3. Сортировка по email");
        System.out.println("4. Сортировка по четным паролям");
        System.out.println("5. Назад");
        System.out.print("\nВыберите действие: ");
    }



    /* Utils methods*/

    private int readInt() {
        while(true) {
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Введите число: ");
                sc.next();
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("\nНажмите Enter...");
        sc.nextLine();
    }
}
