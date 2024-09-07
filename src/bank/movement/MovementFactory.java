package bank.movement;

public class MovementFactory {

    private static Movement depositMovement;
    private static Movement withdrawMovement;

    private MovementFactory() {}

    public static Movement getMovement(int option) {
        if (option == 1) {
            return getDepositMovement();
        }
        if (option == 2) {
            return getWithdrawMovement();
        }
        return null;
    }

    private static Movement getDepositMovement() {
        if (depositMovement == null) {
            depositMovement = new DepositMovement();
        }
        return depositMovement;
    }

    private static Movement getWithdrawMovement() {
        if (withdrawMovement == null) {
            withdrawMovement = new WithdrawMovement();
        }
        return withdrawMovement;
    }

}
