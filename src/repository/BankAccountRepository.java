package repository;

import bank.account.BankAccount;

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

    public BankAccount getBankAccountByDocumentAndPassword(String document, String password) {
        for (BankAccount bankAccount : bankAccounts.values()) {
            if (bankAccount.getDocument().equals(document) &&
                    bankAccount.getPassword().equals(password)) {
                return bankAccount;
            }
        }
        return null;
    }

    public BankAccount getBankAccountByDocument(String document) {
        for (BankAccount bankAccount : bankAccounts.values()) {
            if (bankAccount.getDocument().equals(document)) {
                return bankAccount;
            }
        }
        return null;
    }

}
