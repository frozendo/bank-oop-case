package repository;

import account.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepository {

    private final Map<String, BankAccount> bankAccounts;

    public BankAccountRepository() {
        this.bankAccounts = new HashMap<>();
    }

    public void save(BankAccount bankAccount) {
        bankAccounts.put(bankAccount.getId(), bankAccount);
    }

    public BankAccount get(String id) {
        return bankAccounts.get(id);
    }

}
