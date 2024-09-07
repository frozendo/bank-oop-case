package bank.account;

import bank.payment.FinancialOperationEnum;

public class IndividualAccount extends BankAccount {
    
    private Long specialCreditBalance;

    public IndividualAccount(String name, String document, String password) {
        super(AccountTypeEnum.INDIVIDUAL, name, document, password);
        this.limitCreditBalance = 50_000L;
        this.specialCreditBalance = 0L;
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 11;
    }

    @Override
    public boolean releaseAccountPrize() {
        this.specialCreditBalance = 50_000L;
        return false;
    }

    @Override
    public boolean updateBalance(FinancialOperationEnum financialOperation, long amount) {
        if (FinancialOperationEnum.DEBIT_CARD.equals(financialOperation) || 
                FinancialOperationEnum.TRANSFER.equals(financialOperation)) {
            boolean isDebitPayed = spendAllFromSpecialCredit(amount);
            if (!isDebitPayed) {
                return spendPartialFromSpecialCreditAndUseBalance(financialOperation, amount);
            }
            return false;
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
        boolean updateResult = updateBalance(financialOperation, diff);
        if (updateResult) {
            this.specialCreditBalance = 0L;
            return true;
        }
        return false;
    }
}
