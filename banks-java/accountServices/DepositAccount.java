package accountServices;

import banksServices.Bank;
import clientServices.Client;
import tools.CentralBankException;
import tools.Pair;

import java.util.UUID;

public class DepositAccount implements IAccount{
    private double balance;
    private double percentage;
    private String numberOfAccount;
    private boolean verification;
    public DepositAccount(Client user, Bank bank, double amount) throws CentralBankException {
        if (bank == null) throw new CentralBankException("null bank");
        if (user == null) throw new CentralBankException("null client");
        verification = user.getVerification();
        percentage = bank.getPercentageOnBalanceForDepositAccounts().
                getPairsSumAndPercent().stream().filter(x-> x.getSum() > amount).findFirst().orElse(new Pair(0.0,0.0)).getPercentage();
        balance = amount;
        BelongBank = bank;
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
    }

    public void actionWithAccount()
    {
        balance += balance * percentage / 100;
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
