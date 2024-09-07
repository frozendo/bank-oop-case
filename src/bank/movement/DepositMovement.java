package bank.movement;

import bank.account.BankAccount;
import bank.payment.FinancialOperationEnum;

public class DepositMovement implements Movement {

    @Override
    public String movementMoney(BankAccount bankAccount, double amount) {
        Long centsAmount = convertAmountToCents(amount);
        boolean result = bankAccount.updateBalance(FinancialOperationEnum.DEPOSIT, centsAmount);
        if (result) {
            return "Deposit successful";
        }
        return "Error on process deposit";
    }

    private Long convertAmountToCents(double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        return (long)convertedAmount;
    }

}
