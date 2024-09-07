package bank.payments;

import account.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class TransferPayment implements Payment {

    private final Map<String, Integer> transferPaymentMade;

    private static final Integer MAX_TRANSFER_PER_ACCOUNT = 5;
    private static final String TRANSFER_PAYMENT_SUCCESS = "Transfer payment successful";

    public TransferPayment() {
        this.transferPaymentMade = new HashMap<>();
    }

    @Override
    public String execute(BankAccount bankAccount, long paymentAmount) {
        if (canDoPayment(bankAccount.getId())) {
            boolean result = bankAccount.updateBalance(FinancialOperationEnum.TRANSFER, paymentAmount);
            if (result) {
                updateTransferMadeByAccount(bankAccount.getId());
                return TRANSFER_PAYMENT_SUCCESS;
            }
            return "There is no enough balance to execute payment";
        }
        return "Max transfer achieved for account = " + bankAccount.getId();
    }

    public String execute(BankAccount originBankAccount, BankAccount destinationBankAccount, long paymentAmount) {
        String paymentResult = execute(originBankAccount, paymentAmount);
        if (paymentResult.equals("Payment successful")) {
            boolean depositResult = destinationBankAccount
                    .updateBalance(FinancialOperationEnum.DEPOSIT, paymentAmount);
            if (depositResult) {
                return TRANSFER_PAYMENT_SUCCESS;
            }
            return "Failed to deposit money";
        }
        return paymentResult;
    }

    private boolean canDoPayment(String bankAccountId) {
        int qtdTransferByAccount = transferPaymentMade.getOrDefault(bankAccountId, 0);
        return qtdTransferByAccount >= MAX_TRANSFER_PER_ACCOUNT;
    }

    private void updateTransferMadeByAccount(String bankAccountId) {
        int qtdTransferByAccount = 0;
        if (this.transferPaymentMade.containsKey(bankAccountId)) {
            qtdTransferByAccount = this.transferPaymentMade.get(bankAccountId);
        }
        this.transferPaymentMade.put(bankAccountId, qtdTransferByAccount + 1);
    }

}
