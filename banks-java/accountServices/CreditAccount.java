package accountServices;

import banksServices.Bank;
import clientServices.Client;
import tools.CentralBankException;

import java.util.UUID;

public class CreditAccount implements IAccount {
    private double balance;
    private double commissionUsing;
    private double creditLimit;
    private String numberOfAccount;
    private boolean verification;
    public Bank belongBank;

    public CreditAccount(Client user, Bank bank, double amount) throws CentralBankException {
        if (bank == null) {
            throw new CentralBankException("null bank");
        }
        if (user == null) {
            throw new CentralBankException("null client");
        }
        verification = user.getVerification();
        commissionUsing = bank.getCommissionUsingForCreditAccounts();
        creditLimit = bank.getCreditLimitForCreditAccounts();
        balance = amount;
        belongBank = bank;
        numberOfAccount = UUID.randomUUID().toString();
    }

    public void withdrawalMoney(double amount) {
        if (balance - amount > -creditLimit) {
            balance -= amount;
        }
    }

    public void replenishmentMoney(double amount) {
        balance += amount;
    }

    public void transferMoney(IAccount account, double amount) {
        withdrawalMoney(amount);
        account.replenishmentMoney(amount);
    }

    public void actionWithAccount() {
        if ((balance < 0) && (balance - commissionUsing >= -creditLimit)) {
            balance -= commissionUsing;
        }
    }

    public String getIdAccount() {
        return numberOfAccount;
    }

    public boolean checkVerification() {
        return verification;
    }

    public Bank getBelongBank() {
        return belongBank;
    }
}
