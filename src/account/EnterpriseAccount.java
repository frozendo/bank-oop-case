package account;

public class EnterpriseAccount extends BankAccount {

    private static final long MAX_LOAN_ALLOWED = 1_000_000;

    private long loanBalance;
    private boolean hasLoan;

    public EnterpriseAccount(String name, String document, String password) {
        super(AccountTypeEnum.ENTERPRISE, name, document, password);
        this.limitCreditBalance = 100_000L;
        this.loanBalance = 0L;
        this.hasLoan = false;
    }

    @Override
    public boolean isDocumentValid() {
        return this.getDocument().length() == 14;
    }

    @Override
    public boolean releaseAccountPrize() {
        //enterprise account has no prize
        return false;
    }

    public boolean releaseLoan(long loanAmount) {
        if (!this.hasLoan) {
            if (loanAmount > MAX_LOAN_ALLOWED) {
                return false;
            }
            this.hasLoan = true;
            this.loanBalance = loanAmount;
            return true;
        }
        return false;
    }

    public long payLoan(long paymentAmount) {
        if (this.hasLoan) {
            if (paymentAmount >= loanBalance) {
                this.loanBalance = 0L;
                this.hasLoan = false;
            } else {
                this.loanBalance -= paymentAmount;
            }
        }
        return loanBalance;
    }

}
