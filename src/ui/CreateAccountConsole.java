package ui;

import bank.account.AccountTypeEnum;
import bank.account.BankAccount;
import bank.account.EnterpriseAccount;
import bank.account.IndividualAccount;
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
        AccountTypeEnum accountType = getAccountType();
        if (accountType == null) {
            return null;
        }
        var bankAccount = getAccountDetails(accountType);
        if (!bankAccount.isBankAccountValid()) {
            userMessageHelper.printMessage("Invalid data. Try again or use 'exit' to get back to main menu");
            return createAccount();
        }
        if (!validIfDocumentExists(bankAccount)) {
            return createAccount();
        }
        repository.save(bankAccount);
        userMessageHelper.printMessage("Account successfully created");

        return bankAccount;
    }

    private boolean validIfDocumentExists(BankAccount bankAccount) {
        var existedBankAccount = repository.getBankAccountByDocument(bankAccount.getDocument());
        if (existedBankAccount != null) {
            userMessageHelper.printMessage("Document already exists! Try to use a new one!");
            return false;
        }
        return true;
    }

    private AccountTypeEnum getAccountType() {
        String accountType = userMessageHelper.interactiveMessage("Account Type (Individual or Enterprise)");
        if (validateAccountType(accountType)) {
            return AccountTypeEnum.fromString(accountType);
        }
        if (accountType.equalsIgnoreCase("exit")) {
            return null;
        }
        userMessageHelper.printMessage("Invalid bank.account type! Digit 'Individual' or 'Enterprise'. Use 'exit' to get back to main menu");
        return getAccountType();
    }

    private boolean validateAccountType(String accountType) {
        return accountType.equalsIgnoreCase("individual") ||
                accountType.equalsIgnoreCase("enterprise");
    }

    private BankAccount getAccountDetails(AccountTypeEnum accountType) {
        String documentLengthRequired = getDocumentLengthForAccountType(accountType);

        String name = userMessageHelper.interactiveMessage("What's your name?");
        String document = userMessageHelper.interactiveMessage("Document (%s numbers)".formatted(documentLengthRequired));

        if (AccountTypeEnum.ENTERPRISE.equals(accountType)) {
            return createEnterpriseAccount(name, document);
        }
        return createIndividualAccount(name, document);
    }

    private String getDocumentLengthForAccountType(AccountTypeEnum accountType) {
        if (AccountTypeEnum.ENTERPRISE.equals(accountType)) {
            return "14";
        }
        return "11";
    }

    private BankAccount createIndividualAccount(String name, String document) {
        return new IndividualAccount(
                name,
                document
        );
    }

    private BankAccount createEnterpriseAccount(String name, String document) {
        return new EnterpriseAccount(
                name,
                document
        );
    }

}
