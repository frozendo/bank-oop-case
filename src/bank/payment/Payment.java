package bank.payment;

import account.BankAccount;

public class Payment {

    public String executePayment(BankAccount bankAccount,
                                 FinancialOperationEnum financialOperation,
                                 long paymentAmount) {
        boolean result = bankAccount.updateBalance(financialOperation, paymentAmount);
        return resultMessage(result);
    }

    public String resultMessage(boolean operationResult) {
        if (operationResult) {
            return "Payment successful";
        }
        return "There is no enough balance to execute payment";
    }


}
