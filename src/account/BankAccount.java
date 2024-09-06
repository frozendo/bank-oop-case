package account;

import java.util.UUID;

public class BankAccount {

    private final String id;
    private final String accountType;
    private final String name;
    private final String document;
    private final String password;

    public BankAccount(String accountType, String name, String document, String password) {
        this.id = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.name = name;
        this.document = document;
        this.password = password;
    }

    public boolean isAccountValid() {
        if (this.document.length() < 11) {
            return false;
        }
        return this.password.length() >= 6;
    }

    public String getId() {
        return id;
    }

}
