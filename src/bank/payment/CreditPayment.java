package bank.payment;

import bank.account.BankAccount;

public class CreditPayment {

    public String executeCreditPayment(BankAccount bankAccount, double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        long centsAmount = (long)convertedAmount;
        boolean result = bankAccount.spendCreditBalance(centsAmount);
        if (result) {
            return "Payment successful";
        }
        return "There is no enough credit balance to execute payment";
    }
}
