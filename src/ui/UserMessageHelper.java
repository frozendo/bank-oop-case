package ui;

import java.util.Scanner;

public class UserMessageHelper {

    private final Scanner scanner;

    public UserMessageHelper() {
        this.scanner = new Scanner(System.in);
    }

    public int mainMenu() {
        System.out.println("1. Open Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        String userOption = scanner.next();

        try {
            return Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int bankMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Payment");
        System.out.println("2. Transfer");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Current Amount");
        System.out.println("6. Exit");

        String userOption = scanner.next();

        try {
            return Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String interactiveMessage(String message) {
        System.out.println(message);
        String userValue = scanner.next();
        return userValue;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private void errorOption() {
        System.out.println("Please enter a valid option");
    }

}
