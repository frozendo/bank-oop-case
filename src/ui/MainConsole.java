package ui;

import java.util.Scanner;

public class MainConsole {

    private final Scanner scanner;
    private final CreateAccountConsole createAccountConsole;
    private final LoginConsole loginConsole;

    public MainConsole(Scanner scanner) {
        this.scanner = scanner;
        this.createAccountConsole = new CreateAccountConsole();
        this.loginConsole = new LoginConsole(scanner);
    }

    public void start() {
        System.out.println("1. Open Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        String userOption = scanner.next();
        int option = 0;

        try {
            option = Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            errorOption();
        }

        switch (option) {
            case 1 -> {
                boolean result = createAccountConsole.createAccount();
                if (result) {
                    // login
                } else {
                    start();
                }
            }
            case 2 -> loginConsole.login();
            case 3 -> {
                System.out.println("Thank your for use our system! See you soon!");
                System.exit(0);
            }
            default -> errorOption();
        }

    }

    private void errorOption() {
        System.out.println("Please enter a valid option");
        start();
    }

}
