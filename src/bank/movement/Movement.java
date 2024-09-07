package bank.movement;

import bank.account.BankAccount;

public interface Movement {

    String movementMoney(BankAccount bankAccount, double amount);

}
