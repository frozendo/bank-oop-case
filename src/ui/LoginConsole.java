package ui;

import account.BankAccount;
import repository.BankAccountRepository;
import repository.RepositoryFactory;

public class LoginConsole {

    private final UserMessageHelper userMessageHelper;
    private final BankAccountRepository bankAccountRepository;
    private final BankConsole bankConsole;

    public LoginConsole() {
        this.userMessageHelper = new UserMessageHelper();
        this.bankAccountRepository = RepositoryFactory.getBankAccountRepository();
        this.bankConsole = new BankConsole();
    }

    public void redirectNewAccount(BankAccount bankAccount) {
        bankConsole.startBank(bankAccount);
    }

    public void login() {
        String document = userMessageHelper.interactiveMessage("Inform your document");
        String password = userMessageHelper.interactiveMessage("Inform your password");

        var bankAccount = bankAccountRepository.getBankAccountByDocumentAndPassword(document, password);

        if (bankAccount != null) {
            bankConsole.startBank(bankAccount);
            return;
        }
        userMessageHelper.printMessage("Login failed! Document or password are invalid!");
        userMessageHelper.printMessage("Do you want to try again?");
        failedLogin();
    }

    private void failedLogin() {
        String repeatLogin = userMessageHelper.interactiveMessage("No = 0 or Yes = 1");
        if (repeatLogin.equals("1")) {
            login();
            return;
        }
        if (repeatLogin.equals("0")) {
            return;
        }
        failedLogin();
    }

}
