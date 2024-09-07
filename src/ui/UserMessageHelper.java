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
        System.out.println("2. Movements");
        System.out.println("3. Amount Status");
        System.out.println("4. Go Back");

        String userOption = scanner.next();

        try {
            return Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int paymentMenu() {
        System.out.println("How will be the payment form?");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Transfer");

        String userOption = scanner.next();

        try {
            return Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int movementMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");

        String userOption = scanner.next();

        try {
            return Integer.parseInt(userOption);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Double getAmountForPayment() {
        System.out.println("How much do you want to pay?");
        var userAmount = scanner.next();
        try {
            return Double.parseDouble(userAmount);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String getDestinationAccount() {
        System.out.println("Document that will receive the transfer");
        return scanner.next();
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
