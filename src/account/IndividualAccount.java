package account;

public class IndividualAccount extends BankAccount {

    public IndividualAccount(String name, String document, String password) {
        super(AccountTypeEnum.INDIVIDUAL, name, document, password);
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 11;
    }
}
