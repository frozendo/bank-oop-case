package account;

import java.util.UUID;

public abstract class BankAccount {

    private final String id;
    private final AccountTypeEnum accountType;
    private final String name;
    private final String document;
    private final String password;

    private final Long amount;

    protected BankAccount(AccountTypeEnum accountType, String name, String document, String password) {
        this.id = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.name = name;
        this.document = document;
        this.password = password;

        this.amount = 0L;
    }

    public abstract boolean isDocumentValid();

    public boolean isBankAccountValid() {
        if (this.password.length() < 6) {
            return false;
        }
        return isDocumentValid();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getPassword() {
        return password;
    }
}
