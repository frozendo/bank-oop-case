import ui.ConsoleFactory;
import ui.MainConsole;

public class Main {
    public static void main(String[] args) {
        MainConsole mainConsole = ConsoleFactory.getMainConsole();
        System.out.println("Welcome to bank-oop-system. What do you want to do?");
        mainConsole.start();
    }
}