package ui;

import java.util.Scanner;

public class UserMessageHelper {

    private final Scanner scanner;

    public UserMessageHelper() {
        this.scanner = new Scanner(System.in);
    }

    public String interactiveMessage(String message) {
        System.out.println(message);
        String userValue = scanner.next();
        return userValue;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
