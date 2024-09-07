package ui;

import account.BankAccount;

public class BankConsole {

    private final UserMessageHelper userMessageHelper;

    public BankConsole() {
        this.userMessageHelper = ConsoleFactory.getUserMessageHelper();
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
                case 1 -> {
                    // case 1
                }
                case 2 -> {
                    // case 2
                }
                case 3 -> {
                    // case 3
                }
                case 4 -> {
                    // case 4
                }
                case 5 -> {
                    // case 5
                }
                case 6 -> execute = false;
                default -> bank(bankAccount);
            }
        }
    }

}
