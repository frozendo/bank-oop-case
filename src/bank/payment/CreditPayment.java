package bank.payment;

import bank.account.BankAccount;

public class CreditPayment {
    private final Payment payment;

    public CreditPayment() {
        this.payment = new Payment();
    }

    public String executeCreditPayment(BankAccount bankAccount, long paymentAmount) {
        boolean result = bankAccount.spendCreditBalance(paymentAmount);
        return payment.resultMessage(result);
    }
}
