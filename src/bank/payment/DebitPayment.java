package bank.payments;

import account.BankAccount;

public class DebitPayment implements Payment {

    @Override
    public String execute(BankAccount bankAccount, long paymentAmount) {
        boolean result = bankAccount.updateBalance(FinancialOperationEnum.DEBIT_CARD, paymentAmount);
        if (result) {
            return "Debit payment successful";
        }
        return "There is no enough balance to execute payment";
    }

}
