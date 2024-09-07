package bank.movement;

import bank.account.BankAccount;
import bank.payment.FinancialOperationEnum;

import java.util.HashMap;
import java.util.Map;

public class WithdrawMovement implements Movement {

    private static final Integer MAX_WITHDRAW_BY_ACCOUNT = 5;

    private final Map<String, Integer> withdrawByAccount;

    public WithdrawMovement() {
        this.withdrawByAccount = new HashMap<>();
    }

    @Override
    public String movementMoney(BankAccount bankAccount, double amount) {
        Long centsAmount = convertAmountToCents(amount);
        if (canDoWithdraw(bankAccount.getId())) {
            boolean result = bankAccount.updateBalance(FinancialOperationEnum.WITHDRAW, centsAmount);
            if (result) {
                updateWithdrawByAccount(bankAccount.getId());
                return "Withdraw successful";
            }
            return "There is no enough balance to withdraw";
        }
        return "Max withdraw quantity achieve by bank.account";
    }

    private boolean canDoWithdraw(String bankAccountId) {
        int qtdTransferByAccount = withdrawByAccount.getOrDefault(bankAccountId, 0);
        return qtdTransferByAccount >= MAX_WITHDRAW_BY_ACCOUNT;
    }

    private void updateWithdrawByAccount(String bankAccountId) {
        int qtdTransferByAccount = 0;
        if (this.withdrawByAccount.containsKey(bankAccountId)) {
            qtdTransferByAccount = this.withdrawByAccount.get(bankAccountId);
        }
        this.withdrawByAccount.put(bankAccountId, qtdTransferByAccount + 1);
    }

    private Long convertAmountToCents(double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        return (long)convertedAmount;
    }

}
