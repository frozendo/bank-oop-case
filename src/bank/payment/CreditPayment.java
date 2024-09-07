package bank.payment;

import bank.account.BankAccount;

public class CreditPayment {

    public String executeCreditPayment(BankAccount bankAccount, double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        long longAmount = (long)convertedAmount;
        boolean result = bankAccount.spendCreditBalance(longAmount);
        if (result) {
            return "Payment successful";
        }
        return "There is no enough credit balance to execute payment";
    }
}
