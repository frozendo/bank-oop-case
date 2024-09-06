package account;

public class EnterpriseAccount extends BankAccount {

    private final Long creditAmount;

    public EnterpriseAccount(String name, String document, String password) {
        super(AccountTypeEnum.ENTERPRISE, name, document, password);
        this.creditAmount = 10000L;
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 14;
    }

}
