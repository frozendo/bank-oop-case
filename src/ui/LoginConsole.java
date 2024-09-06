package ui;

import java.util.Scanner;

public class LoginConsole {

    private final Scanner scanner;
    private final BankConsole bankConsole;

    public LoginConsole(Scanner scanner) {
        this.scanner = scanner;
        this.bankConsole = new BankConsole(scanner);
    }

    public void login() {
        System.out.println("Document");
        String document = scanner.next();
        System.out.println("Password");
        String password = scanner.next();

        if (true) {
            bankConsole.bank("");
        }

        System.out.println("Bye ");
    }

}
