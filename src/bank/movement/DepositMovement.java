package bank.movement;

import bank.account.BankAccount;
import bank.payment.FinancialOperationEnum;

public class DepositMovement implements Movement {

    @Override
    public String movementMoney(BankAccount bankAccount, FinancialOperationEnum financialOperation, long amount) {
        boolean result = bankAccount.updateBalance(financialOperation, amount);
        if (result) {
            return "Deposit successful";
        }
        return "Error on process deposit";
    }

}
