package ui;

import java.util.Scanner;

public class MainConsole {

    private final Scanner scanner;
    private final CreateAccountConsole createAccountConsole;
    private final LoginConsole loginConsole;

    public MainConsole(Scanner scanner) {
        this.scanner = scanner;
        this.createAccountConsole = new CreateAccountConsole(scanner);
        this.loginConsole = new LoginConsole(scanner);
    }

    public void start() {

        System.out.println("Welcome to bank-oop-system. What do you want to do?");
        System.out.println("1. Open Account");
        System.out.println("2. Login");
        System.out.println("Any other number to exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1 -> {
                createAccountConsole.createAccount();
                start();
            }
            case 2 -> loginConsole.login();
            default -> {
                System.out.println("Thank your for use our system! See you soon!");
                System.exit(0);
            }
        }

    }

}
