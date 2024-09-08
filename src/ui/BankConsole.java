package ui;

import bank.account.AccountTypeEnum;
import bank.account.BankAccount;
import bank.account.IndividualAccount;
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
                default -> userMessageHelper.printMessage("Invalid option! Inform a number between 1 and 4");
            }
        }
    }

    private void doPayment(BankAccount bankAccount) {
        int paymentChoose = userMessageHelper.paymentMenu();
        var paymentAmount = userMessageHelper.getAmountForPayment("pay");

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

    }

    private void doMovement(BankAccount bankAccount) {
        int movementChoose = userMessageHelper.movementMenu();
        String action = movementChoose == 1 ? "deposit" : "withdraw";
        var paymentAmount = userMessageHelper.getAmountForPayment(action);

        String result;

        Movement movement = MovementFactory.getMovement(movementChoose);
        if (movement == null) {
            result = "Invalid movement option";
        } else {
            result = movement.movementMoney(bankAccount, paymentAmount);
        }

        userMessageHelper.printMessage(result);
    }

    private void doAccountStatus(BankAccount bankAccount) {
        userMessageHelper.printMessage("----------------");
        userMessageHelper.printMessage("Account Status");
        userMessageHelper.printMessage("----------------");

        userMessageHelper.printMessage("Current Balance = " + bankAccount.getBalance());
        userMessageHelper.printMessage("Current Credit Balance = " + bankAccount.getCurrentCreditBalance());
        userMessageHelper.printMessage("Current Available Credit = " + bankAccount.getAvailableLimit());

        if (AccountTypeEnum.INDIVIDUAL.equals(bankAccount.getAccountType())) {
            IndividualAccount individualAccount = (IndividualAccount) bankAccount;
            userMessageHelper.printMessage("Current Special Prize Balance = " + individualAccount.getSpecialCreditBalance());
        }
    }

}
