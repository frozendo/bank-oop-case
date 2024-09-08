package bank.account;

import bank.payment.FinancialOperationEnum;

public class IndividualAccount extends BankAccount {
    
    private Long specialCreditBalance;

    public IndividualAccount(String name, String document) {
        super(AccountTypeEnum.INDIVIDUAL, name, document);
        this.limitCreditBalance = 50_000L;
        this.specialCreditBalance = 0L;
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 11;
    }

    @Override
    public void releaseAccountPrize() {
        this.specialCreditBalance = 50_000L;
    }

    @Override
    public boolean updateBalance(FinancialOperationEnum financialOperation, long amount) {
        if (FinancialOperationEnum.DEBIT_CARD.equals(financialOperation)) {
            boolean isDebitPayed = spendAllFromSpecialCredit(amount);
            if (!isDebitPayed) {
                return spendPartialFromSpecialCreditAndUseBalance(financialOperation, amount);
            }
            return true;
        }
        return super.updateBalance(financialOperation, amount);
    }

    private boolean spendAllFromSpecialCredit(long amount) {
        if (specialCreditBalance >= amount) {
            this.specialCreditBalance -= amount;
            return true;
        }
        return false;
    }

    private boolean spendPartialFromSpecialCreditAndUseBalance(FinancialOperationEnum financialOperation, long amount) {
        long diff = amount - specialCreditBalance;
        boolean updateResult = super.updateBalance(financialOperation, diff);
        if (updateResult) {
            this.specialCreditBalance = 0L;
            return true;
        }
        return false;
    }

    public double getSpecialCreditBalance() {
        return (double) specialCreditBalance / 100;
    }
}
