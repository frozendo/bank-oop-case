package ui;

import account.BankAccount;
import repository.BankAccountRepository;
import repository.RepositoryFactory;

public class CreateAccountConsole {

    private final UserMessageHelper userMessageHelper;
    private final BankAccountRepository repository;

    public CreateAccountConsole() {
        this.userMessageHelper = ConsoleFactory.getUserMessageHelper();
        this.repository = RepositoryFactory.getBankAccountRepository();
    }

    public BankAccount createAccount() {
        String accountType = getAccountType();
        if (accountType.equalsIgnoreCase("exit")) {
            return null;
        }
        var bankAccount = getAccountDetails(accountType);
        if (bankAccount.isAccountValid()) {
            repository.save(bankAccount);
            userMessageHelper.printMessage("Account successfully created");
        } else {
            userMessageHelper.printMessage("Invalid data. Try again or use 'exit' to get back to main menu");
            return createAccount();
        }
        return bankAccount;
    }

    private String getAccountType() {
        String accountType = userMessageHelper.interactiveMessage("Account Type (Individual or Enterprise)");
        if (validateAccountType(accountType)) {
            return accountType;
        }
        userMessageHelper.printMessage("Invalid account type! Digit 'Individual' or 'Enterprise'. Use 'exit' to get back to main menu");
        return getAccountType();
    }

    private boolean validateAccountType(String accountType) {
        return accountType.equalsIgnoreCase("individual") ||
                accountType.equalsIgnoreCase("enterprise") ||
                accountType.equalsIgnoreCase("exit");
    }

    private BankAccount getAccountDetails(String accountType) {
        String name = userMessageHelper.interactiveMessage("What's your name?");
        String document = userMessageHelper.interactiveMessage("Document (11 numbers)");
        String password = userMessageHelper.interactiveMessage("Password (At least 6 characters)");

        return new BankAccount(
                accountType,
                name,
                document,
                password
        );
    }

}
