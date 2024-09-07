package bank.payment;

import bank.account.BankAccount;

public class TransferPayment {
    private final Payment payment;

    public TransferPayment() {
        this.payment = new Payment();
    }

    public String executeTransferPayment(BankAccount originBankAccount,
                                         BankAccount destinationBankAccount,
                                         long paymentAmount) {
        String paymentResult = payment.executePayment(originBankAccount, FinancialOperationEnum.TRANSFER, paymentAmount);
        if (paymentResult.equals("Payment successful")) {
            boolean depositResult = destinationBankAccount
                    .updateBalance(FinancialOperationEnum.DEPOSIT, paymentAmount);
            if (depositResult) {
                return "Payment successful";
            }
            return "Failed to deposit money";
        }
        return paymentResult;
    }

}
