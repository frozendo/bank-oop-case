package bank.account;

import bank.payment.FinancialOperationEnum;

import java.util.UUID;

public abstract class BankAccount {

    private final String id;
    private final AccountTypeEnum accountType;
    private final String name;
    private final String document;
    private final String password;

    private Long balance;
    private Long currentCreditBalance;
    protected Long limitCreditBalance;

    protected BankAccount(AccountTypeEnum accountType, String name, String document, String password) {
        this.id = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.name = name;
        this.document = document;
        this.password = password;

        this.balance = 0L;
        this.currentCreditBalance = 0L;
    }

    public abstract boolean isDocumentValid();
    public abstract boolean releaseAccountPrize();

    public boolean isBankAccountValid() {
        if (this.password.length() < 6) {
            return false;
        }
        return isDocumentValid();
    }

    private void increaseBalance(long amount) {
        this.balance += amount;
    }

    private boolean updateBalance(long amount) {
        if (this.balance < amount) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    public boolean updateBalance(FinancialOperationEnum financialOperationEnum, long amount) {
        if (FinancialOperationEnum.DEPOSIT.equals(financialOperationEnum)) {
            increaseBalance(amount);
            return true;
        }

        return updateBalance(amount);
    }

    public boolean spendCreditBalance(long amount) {
        long expectedCreditBalance = this.currentCreditBalance + amount;
        if (expectedCreditBalance > this.limitCreditBalance) {
            return false;
        }
        this.currentCreditBalance += amount;
        return true;
    }

    public void releaseCreditBalance(long amount) {
        if (currentCreditBalance > 0) {
            currentCreditBalance -= amount;
        }
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

    public Long getBalance() {
        return balance;
    }

    public Long getCurrentCreditBalance() {
        return currentCreditBalance;
    }
}
