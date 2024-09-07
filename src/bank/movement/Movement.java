package bank.movement;

import account.BankAccount;
import bank.payment.FinancialOperationEnum;

public interface Movement {

    String movementMoney(BankAccount bankAccount, FinancialOperationEnum financialOperation, long amount);

}
