package banksServices;

public class Transaction {
    private String withdrawalAccount;
    private String transferAccount;
    private double amount;

    public Transaction(String withdrawalAccount, String transferAccount, double amount) {
        this.withdrawalAccount = withdrawalAccount;
        this.transferAccount = transferAccount;
        this.amount = amount;
    }

    public String getWithdrawalAccount() {
        return withdrawalAccount;
    }
    public void setWithdrawalAccount(String value) {
        this.withdrawalAccount = value;
    }

    public String getTransferAccount() {
        return transferAccount;
    }
    public void setTransferAccount(String value) {
        this.transferAccount = value;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double value) {
        this.amount = value;
    }

    public String toString() {
        return this.withdrawalAccount + " to " + this.transferAccount + "of" + this.amount;
    }
}
