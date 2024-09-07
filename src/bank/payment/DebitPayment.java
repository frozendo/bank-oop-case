package bank.payment;

import bank.account.BankAccount;

public class DebitPayment {
    private final Payment payment;

    public DebitPayment() {
        this.payment = new Payment();
    }

    public String executeDebitPayment(BankAccount bankAccount, long paymentAmount) {
        return payment.executePayment(bankAccount, FinancialOperationEnum.DEBIT_CARD, paymentAmount);
    }

}
