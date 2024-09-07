package bank.payments;

import account.BankAccount;

public class CreditPayment implements Payment {
    @Override
    public String execute(BankAccount bankAccount, long paymentAmount) {
        boolean result = bankAccount.spendCreditBalance(paymentAmount);
        if (result) {
            return "Credit payment successful";
        }
        return "There is no enough credit balance to execute payment";
    }
}
