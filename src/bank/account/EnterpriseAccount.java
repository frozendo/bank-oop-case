package bank.account;

public class EnterpriseAccount extends BankAccount {

    public EnterpriseAccount(String name, String document) {
        super(AccountTypeEnum.ENTERPRISE, name, document);
        this.limitCreditBalance = 100_000L;
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 14;
    }

    @Override
    public void releaseAccountPrize() {
        throw new UnsupportedOperationException("Not supported operation.");
    }

}
