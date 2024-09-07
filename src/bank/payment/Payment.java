package bank.payment;

import bank.account.BankAccount;

public class Payment {

    public String executePayment(BankAccount bankAccount,
                                 FinancialOperationEnum financialOperation,
                                 double paymentAmount) {
        boolean result = bankAccount.updateBalance(financialOperation, convertAmountToCents(paymentAmount));
        return resultMessage(result);
    }

    private String resultMessage(boolean operationResult) {
        if (operationResult) {
            return "Payment successful";
        }
        return "There is no enough balance to execute payment";
    }

    private Long convertAmountToCents(double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        return (long)convertedAmount;
    }


}
