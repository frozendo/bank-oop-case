package bank.account;

import bank.payment.FinancialOperationEnum;

import java.util.UUID;

public abstract class BankAccount {

    private final String id;
    private final AccountTypeEnum accountType;
    private final String name;
    private final String document;

    private Long balance;
    private Long currentCreditBalance;
    protected Long limitCreditBalance;

    protected BankAccount(AccountTypeEnum accountType, String name, String document) {
        this.id = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.name = name;
        this.document = document;

        this.balance = 0L;
        this.currentCreditBalance = 0L;
    }

    public abstract boolean isDocumentValid();
    public abstract void releaseAccountPrize();

    public boolean isBankAccountValid() {
        return isDocumentValid();
    }

    public boolean updateBalance(FinancialOperationEnum financialOperationEnum, long amount) {
        if (FinancialOperationEnum.DEPOSIT.equals(financialOperationEnum)) {
            increaseBalance(amount);
            return true;
        }

        return decreaseBalance(amount);
    }

    public boolean spendCreditBalance(long amount) {
        long expectedCreditBalance = this.currentCreditBalance + amount;
        if (expectedCreditBalance > this.limitCreditBalance) {
            return false;
        }
        this.currentCreditBalance += amount;
        return true;
    }

    private void increaseBalance(long amount) {
        this.balance += amount;
    }

    private boolean decreaseBalance(long amount) {
        if (this.balance < amount) {
            return false;
        }

        this.balance -= amount;
        return true;
    }

    public String getId() {
        return id;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public double getBalance() {
        return (double) balance / 100;
    }

    public double getCurrentCreditBalance() {
        return (double) currentCreditBalance / 100;
    }

    public double getAvailableLimit() {
        return (double) (limitCreditBalance - currentCreditBalance) / 100;
    }
}
