package ui;

import bank.account.BankAccount;
import bank.movement.Movement;
import bank.movement.MovementFactory;
import bank.payment.PaymentFactory;
import repository.BankAccountRepository;
import repository.RepositoryFactory;

public class BankConsole {

    private final UserMessageHelper userMessageHelper;
    private final BankAccountRepository repository;

    public BankConsole() {
        this.userMessageHelper = ConsoleFactory.getUserMessageHelper();
        this.repository = RepositoryFactory.getBankAccountRepository();
    }

    public void startBank(BankAccount bankAccount) {
        userMessageHelper.printMessage("------------------------");
        userMessageHelper.printMessage("Welcome " + bankAccount.getName() + "!!!");
        userMessageHelper.printMessage("------------------------");
        bank(bankAccount);
    }

    private void bank(BankAccount bankAccount) {
        boolean execute = true;

        while (execute) {
            int option = userMessageHelper.bankMenu();

            switch (option) {
                case 1 -> doPayment(bankAccount);
                case 2 -> doMovement(bankAccount);
                case 3 -> doAccountStatus(bankAccount);
                case 4 -> execute = false;
                default -> bank(bankAccount);
            }
        }
    }

    private void doPayment(BankAccount bankAccount) {
        int paymentChoose = userMessageHelper.paymentMenu();
        var paymentAmount = userMessageHelper.getAmountForPayment();

        String result;

        switch (paymentChoose) {
            case 1 -> {
                var paymentMethod = PaymentFactory.getCreditPayment();
                result = paymentMethod.executeCreditPayment(bankAccount, paymentAmount);
            }
            case 2 -> {
                var paymentMethod = PaymentFactory.getDebitPayment();
                result = paymentMethod.executeDebitPayment(bankAccount, paymentAmount);
            }
            case 3 -> {
                var paymentMethod = PaymentFactory.getTransferPayment();
                var destinationDocument = userMessageHelper.getDestinationAccount();

                var destinationAccount = repository.getBankAccountByDocument(destinationDocument);

                if (destinationAccount != null) {
                    result = paymentMethod
                            .executeTransferPayment(bankAccount, destinationAccount, paymentAmount);
                } else {
                    result = "Destination Account not found";
                }
            }
            default -> result = "Invalid option!!";
        }

        userMessageHelper.printMessage(result);
        bank(bankAccount);

    }

    private void doMovement(BankAccount bankAccount) {
        int movementChoose = userMessageHelper.paymentMenu();
        var paymentAmount = userMessageHelper.getAmountForPayment();

        String result;

        Movement movement = MovementFactory.getMovement(movementChoose);
        if (movement == null) {
            result = "Invalid movement option";
        } else {
            result = movement.movementMoney(bankAccount, paymentAmount);
        }

        userMessageHelper.printMessage(result);
        bank(bankAccount);
    }

    private void doAccountStatus(BankAccount bankAccount) {
        userMessageHelper.printMessage("----------------");
        userMessageHelper.printMessage("Account Status");
        userMessageHelper.printMessage("----------------");

        userMessageHelper.printMessage("Current Balance = " + bankAccount.getBalance());
        userMessageHelper.printMessage("Current Credit Balance = " + bankAccount.getCurrentCreditBalance());
        bank(bankAccount);
    }

}
