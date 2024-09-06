package ui;

import java.util.Scanner;

public class CreateAccountConsole {

    private final Scanner scanner;

    public CreateAccountConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createAccount() {
        System.out.println("Account Type (Individual or Enterprise)");
        String accountType = scanner.next();
        System.out.println("Name");
        String name = scanner.next();
        System.out.println("Document");
        String document =scanner.next();
        System.out.println("Password");
        String password = scanner.next();
    }

}
