package ui;

public class ConsoleFactory {

    private ConsoleFactory() {}

    private static BankConsole bankConsole;
    private static CreateAccountConsole createAccountConsole;
    private static LoginConsole loginConsole;
    private static MainConsole mainConsole;
    private static UserMessageHelper userMessageHelper;

    public static BankConsole getBankConsole() {
        if (bankConsole == null) {
            bankConsole = new BankConsole();
        }
        return bankConsole;
    }

    public static CreateAccountConsole getCreateAccountConsole() {
        if (createAccountConsole == null) {
            createAccountConsole = new CreateAccountConsole();
        }
        return createAccountConsole;
    }

    public static LoginConsole getLoginConsole() {
        if (loginConsole == null) {
            loginConsole = new LoginConsole();
        }
        return loginConsole;
    }

    public static MainConsole getMainConsole() {
        if (mainConsole == null) {
            mainConsole = new MainConsole();
        }
        return mainConsole;
    }

    public static UserMessageHelper getUserMessageHelper() {
        if (userMessageHelper == null) {
            userMessageHelper = new UserMessageHelper();
        }
        return userMessageHelper;
    }

}
