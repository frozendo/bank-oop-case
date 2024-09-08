package ui;

import bank.account.BankAccount;
import bank.account.EnterpriseAccount;
import bank.account.IndividualAccount;
import repository.BankAccountRepository;
import repository.RepositoryFactory;

public class MainConsole {

    private final UserMessageHelper userMessageHelper;
    private final CreateAccountConsole createAccountConsole;
    private final LoginConsole loginConsole;
    private final BankAccountRepository repository;

    public MainConsole() {
        this.userMessageHelper = ConsoleFactory.getUserMessageHelper();
        this.createAccountConsole = ConsoleFactory.getCreateAccountConsole();
        this.loginConsole = ConsoleFactory.getLoginConsole();
        this.repository = RepositoryFactory.getBankAccountRepository();
    }

    public void start() {

        BankAccount individualAccount = new IndividualAccount("john", "12345678901");
        BankAccount enterpriseAccount = new EnterpriseAccount("acme", "12345678901234");

        repository.save(individualAccount);
        repository.save(enterpriseAccount);

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
