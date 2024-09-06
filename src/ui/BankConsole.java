package ui;

import java.util.Scanner;

public class BankConsole {

    private final Scanner scanner;

    public BankConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void bank(String username) {
        boolean execute = true;

        while (execute) {
            System.out.println("Welcome " + username + ". What do you want to do?");
            System.out.println("1. Payment");
            System.out.println("2. Transfer");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Current Amount");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    // case 1
                }
                case 2 -> {
                    // case 2
                }
                case 3 -> {
                    // case 3
                }
                case 4 -> {
                    // case 4
                }
                case 5 -> {
                    // case 5
                }
                default -> execute = false;
            }
        }
    }

}
