package bank.payments;

import account.BankAccount;

public interface Payment {

    String execute(BankAccount bankAccount, long paymentAmount);


}
