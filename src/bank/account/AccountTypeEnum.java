package bank.account;

public enum AccountTypeEnum {
    INDIVIDUAL, ENTERPRISE;

    public static AccountTypeEnum fromString(String name) {
        return AccountTypeEnum.valueOf(name.toUpperCase());
    }
}
