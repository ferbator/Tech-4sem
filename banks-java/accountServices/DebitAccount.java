package accountServices;

import banksServices.Bank;
import clientServices.Client;
import tools.CentralBankException;

import java.util.UUID;

public class DebitAccount implements IAccount{
    private double balance;
    private double percentageOnBalance;
    private String numberOfAccount;
    private boolean verification;
    public DebitAccount(Client user, Bank bank, double amount) throws CentralBankException {
        if (bank == null) throw new CentralBankException("null bank");
        if (user == null) throw new CentralBankException("null client");
        verification = user.getVerification();
        percentageOnBalance = bank.getPercentageOnBalanceForDebitAccounts();
        BelongBank = bank;
        balance = amount;
        numberOfAccount = UUID.randomUUID().toString();
    }

    public Bank BelongBank;

    public void withdrawalMoney(double amount)
    {
        balance -= amount;
    }

    public void replenishmentMoney(double amount)
    {
        balance += amount;
    }

    public void transferMoney(IAccount account, double amount)
    {
        withdrawalMoney(amount);
        account.replenishmentMoney(amount);
    }

    public void actionWithAccount()
    {
        balance += balance * percentageOnBalance / 100;
    }

    public String getIdAccount()
    {
        return numberOfAccount;
    }

    public boolean checkVerification()
    {
        return verification;
    }

    public Bank getBelongBank()
    {
        return BelongBank;
    }
}
