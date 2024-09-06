import ui.MainConsole;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainConsole mainConsole = new MainConsole(scanner);
        mainConsole.start();
    }
}