package ui;

public class MainConsole {

    private final UserMessageHelper userMessageHelper;
    private final CreateAccountConsole createAccountConsole;
    private final LoginConsole loginConsole;

    public MainConsole() {
        this.userMessageHelper = new UserMessageHelper();
        this.createAccountConsole = new CreateAccountConsole();
        this.loginConsole = new LoginConsole();
    }

    public void start() {
        int option = userMessageHelper.mainMenu();

        switch (option) {
            case 1 -> {
                var bankAccount = createAccountConsole.createAccount();
                if (bankAccount != null) {
                    loginConsole.redirectNewAccount(bankAccount);
                }
                start();
            }
            case 2 -> {
                loginConsole.login();
                start();
            }
            case 3 -> {
                userMessageHelper.printMessage("Thank your for use our system! See you soon!");
                System.exit(0);
            }
            default -> errorOption();
        }

    }

    private void errorOption() {
        userMessageHelper.printMessage("Please enter a valid option");
        start();
    }

}
