package bank.payment;

public class PaymentFactory {

    private PaymentFactory() {}

    private static CreditPayment creditPayment;
    private static DebitPayment debitPayment;
    private static TransferPayment transferPayment;

    public static CreditPayment getCreditPayment() {
        if (creditPayment == null) {
            creditPayment = new CreditPayment();
        }
        return creditPayment;
    }

    public static DebitPayment getDebitPayment() {
        if (debitPayment == null) {
            debitPayment = new DebitPayment();
        }
        return debitPayment;
    }

    public static TransferPayment getTransferPayment() {
        if (transferPayment == null) {
            transferPayment = new TransferPayment();
        }
        return transferPayment;
    }

}
