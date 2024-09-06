package repository;

public class RepositoryFactory {

    private static BankAccountRepository bankAccountRepository;

    public static BankAccountRepository getBankAccountRepository() {
        if (bankAccountRepository == null) {
            bankAccountRepository = new BankAccountRepository();
        }
        return bankAccountRepository;
    }

}
