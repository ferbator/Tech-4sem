package banksServices;

import accountServices.DepositAccountPercentage;
import accountServices.IAccount;
import clientServices.Client;
import tools.CentralBankException;
import tools.EventRegistrar;

import java.util.*;

public class Bank {
    public EventRegistrar events;
    private HashMap<Client, ArrayList<IAccount>> baseBank;
    private ArrayList<Transaction> transactions;
    private double limitForNotVerification;
    private double creditLimitForCreditAccounts;
    private double commissionUsingForCreditAccounts;
    private double percentageOnBalanceForDebitAccounts;
    private String name;
    private DepositAccountPercentage percentageOnBalanceForDepositAccounts;

    public Bank(String name, double limitForNotVerification, double creditLimitForCreditAccounts, double commissionUsingForCreditAccounts,
                DepositAccountPercentage percentageOnBalanceForDepositAccounts, double percentageOnBalanceForDebitAccounts) throws CentralBankException {
        if (name == null) throw new CentralBankException("Incorrect name");
        this.name = name;
        this.limitForNotVerification = limitForNotVerification;
        this.creditLimitForCreditAccounts = creditLimitForCreditAccounts;
        this.commissionUsingForCreditAccounts = commissionUsingForCreditAccounts;
        this.percentageOnBalanceForDepositAccounts = percentageOnBalanceForDepositAccounts;
        this.percentageOnBalanceForDebitAccounts = percentageOnBalanceForDebitAccounts;
        this.baseBank = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.events = new EventRegistrar("Change name",
                "Change creditLimitForCreditAccounts",
                "Change commissionUsingForCreditAccounts",
                "Change limitForNotVerification",
                "Change percentageOnBalanceForDebitAccounts",
                "Change percentageOnBalanceForDepositAccounts");
    }

    // public delegate void ChangeFieldInBanks(double other);
    // public event ChangeFieldInBanks ChangeFieldInBank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        events.notify("Change name");
        this.name = name;
    }

    public double getCreditLimitForCreditAccounts() {
        return creditLimitForCreditAccounts;
    }

    public void setCreditLimitForCreditAccounts(double value) {
        events.notify("Change creditLimitForCreditAccounts");
        this.creditLimitForCreditAccounts = value;
    }

    public double getCommissionUsingForCreditAccounts() {
        return commissionUsingForCreditAccounts;
    }

    public void setCommissionUsingForCreditAccounts(double value) {
        events.notify("Change commissionUsingForCreditAccounts");
        this.commissionUsingForCreditAccounts = value;
    }

    public double getLimitForNotVerification() {
        return limitForNotVerification;
    }

    public void setLimitForNotVerification(double value) {
        events.notify("Change limitForNotVerification");
        this.limitForNotVerification = value;
    }

    public double getPercentageOnBalanceForDebitAccounts() {
        return percentageOnBalanceForDebitAccounts;
    }

    public void setPercentageOnBalanceForDebitAccounts(double value) {
        events.notify("Change percentageOnBalanceForDebitAccounts");
        this.percentageOnBalanceForDebitAccounts = value;
    }

    public DepositAccountPercentage getPercentageOnBalanceForDepositAccounts() {
        return percentageOnBalanceForDepositAccounts;
    }

    public void setPercentageOnBalanceForDepositAccounts(DepositAccountPercentage value) {
        events.notify("Change percentageOnBalanceForDepositAccounts");
        this.percentageOnBalanceForDepositAccounts = value;
    }

    public void registerClient(Client client, IAccount account) throws CentralBankException {
        if (client == null) throw new CentralBankException("Incorrect client");
        if (account == null) throw new CentralBankException("Incorrect account");
        if (baseBank.containsKey(client))
            baseBank.get(client).add(account);
        else
            baseBank.put(client, new ArrayList<IAccount>(List.of(account)));
        client.createAccount(this, getInfoAccounts(client));
    }

    public IAccount findAccount(String numberId) throws CentralBankException {
        if (numberId == null) throw new CentralBankException("Incorrect numberId");
        return baseBank.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(i -> Objects.equals(i.getIdAccount(), numberId))
                .findFirst()
                .orElse(null);
    }

    public void accruePercentage() {
        baseBank.values().stream().flatMap(Collection::stream).forEach(IAccount::actionWithAccount);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    private ArrayList<IAccount> getInfoAccounts(Client client) {
        return baseBank.getOrDefault(client, null);
    }
}
