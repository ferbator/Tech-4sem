package accountServices;

import banksServices.Bank;

public interface IAccount {
    void withdrawalMoney(double amount);
    void replenishmentMoney(double amount);
    void transferMoney(IAccount account, double amount);
    void actionWithAccount();
    String getIdAccount();
    boolean checkVerification();
    Bank getBelongBank();
}
