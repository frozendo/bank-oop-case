package bank.movement;

import bank.account.AccountTypeEnum;
import bank.account.BankAccount;
import bank.payment.FinancialOperationEnum;

import java.util.HashMap;
import java.util.Map;

public class DepositMovement implements Movement {

    private static final Integer DEPOSIT_NUMBER_TO_WIN_PRIZE = 5;

    private final Map<String, Integer> depositByAccount;

    public DepositMovement() {
        this.depositByAccount = new HashMap<>();
    }

    @Override
    public String movementMoney(BankAccount bankAccount, double amount) {
        String successMessage = "Deposit successful";
        Long centsAmount = convertAmountToCents(amount);
        boolean result = bankAccount.updateBalance(FinancialOperationEnum.DEPOSIT, centsAmount);
        if (result) {
            if (shouldReleasePrize(bankAccount)) {
                releaseIndividualAccountPrize(bankAccount);
                successMessage = "Congratulations, you get the prize after the fifth successful deposit";
            }
            updateDepositByAccount(bankAccount.getId());
            return successMessage;
        }
        return "Error on process deposit";
    }

    private Long convertAmountToCents(double paymentAmount) {
        double convertedAmount = paymentAmount * 100;
        return (long)convertedAmount;
    }

    private boolean shouldReleasePrize(BankAccount bankAccount) {
        int qtdTransferByAccount = depositByAccount.getOrDefault(bankAccount.getId(), 0);
        return AccountTypeEnum.INDIVIDUAL.equals(bankAccount.getAccountType()) &&
                (qtdTransferByAccount + 1) == DEPOSIT_NUMBER_TO_WIN_PRIZE;
    }

    private void releaseIndividualAccountPrize(BankAccount bankAccount) {
        bankAccount.releaseAccountPrize();
    }

    private void updateDepositByAccount(String bankAccountId) {
        int qtdDepositByAccount = 0;
        if (this.depositByAccount.containsKey(bankAccountId)) {
            qtdDepositByAccount = this.depositByAccount.get(bankAccountId);
        }
        this.depositByAccount.put(bankAccountId, qtdDepositByAccount + 1);
    }

}
