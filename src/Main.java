import ui.MainConsole;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainConsole mainConsole = new MainConsole(scanner);
        System.out.println("Welcome to bank-oop-system. What do you want to do?");
        mainConsole.start();
    }
}