package banksServices;

import accountServices.*;
import clientServices.Client;
import tools.CentralBankException;

import java.util.ArrayList;
import java.util.Objects;

public class CentralBank {
    private ArrayList<Bank> banks;
    private ArrayList<Transaction> transactions;

    public CentralBank() {
        banks = new ArrayList<Bank>();
        transactions = new ArrayList<Transaction>();
    }

    public Bank addBankToBase(String name, double limitForNotVerification, double creditLimitForCreditAccounts, double commissionUsingForCreditAccounts, DepositAccountPercentage percentageOnBalanceForDepositAccounts, double percentageOnBalanceForDebitAccounts) throws CentralBankException {
        banks.add(new Bank(name, limitForNotVerification, creditLimitForCreditAccounts, commissionUsingForCreditAccounts, percentageOnBalanceForDepositAccounts, percentageOnBalanceForDebitAccounts));
        return banks.get(banks.size() - 1);
    }

    public IAccount regAccountClientInBank(Bank bank, Client client, AccountOption option, double amount) throws CentralBankException {
        if (bank == null) throw new CentralBankException("null bank");
        if (client == null) throw new CentralBankException("null client");
        if (!banks.contains(bank)) throw new CentralBankException("Bank dont registered");
        if (amount < 0) throw new CentralBankException("Negative balance");
        IAccount account;
        switch (option) {
            case Credit:
                account = new CreditAccount(client, bank, amount);
                bank.registerClient(client, account);
                return account;
            case Deposit:
                account = new DepositAccount(client, bank, amount);
                bank.registerClient(client, account);
                return account;
            case Debit:
                account = new DebitAccount(client, bank, amount);
                bank.registerClient(client, account);
                return account;
            default:
                throw new CentralBankException("{option} - Incorrect options");
        }
    }

    public Transaction withdrawalMoney(IAccount account, double amount) throws CentralBankException {
        if (!account.checkVerification() && account.getBelongBank().getLimitForNotVerification() < amount)
            throw new CentralBankException("Attempt to withdraw money from an unverified account");
        var tmpTransaction = new Transaction(account.getIdAccount(), null, amount);
        transactions.add(tmpTransaction);
        account.getBelongBank().addTransaction(tmpTransaction);
        account.withdrawalMoney(amount);
        return transactions.get(transactions.size() - 1);
    }

    public Transaction replenishmentMoney(IAccount account, double amount) {
        var tmpTransaction = new Transaction(null, account.getIdAccount(), amount);
        transactions.add(tmpTransaction);
        account.getBelongBank().addTransaction(tmpTransaction);
        account.replenishmentMoney(amount);
        return transactions.get(transactions.size() - 1);
    }

    public Transaction transferMoney(IAccount account1, IAccount account2, double amount) throws CentralBankException {
        if (!account1.checkVerification() && account1.getBelongBank().getLimitForNotVerification() < amount)
            throw new CentralBankException("Attempt to withdraw money from an unverified account");
        var tmpTransaction = new Transaction(account1.getIdAccount(), account2.getIdAccount(), amount);
        transactions.add(tmpTransaction);
        account1.getBelongBank().addTransaction(tmpTransaction);
        account2.getBelongBank().addTransaction(tmpTransaction);
        account1.transferMoney(account2, amount);
        return transactions.get(transactions.size() - 1);
    }

    public void cancelTransaction(Transaction transaction) throws CentralBankException {
        if (transaction == null) throw new CentralBankException("Incorrect transaction");
        IAccount tmpTransferAccount = null;
        IAccount tmpWithdrawalAccount = null;
        if (transaction.getTransferAccount() != null && transaction.getWithdrawalAccount() != null) {
            for(Bank bank : banks)
            {
                tmpTransferAccount = bank.findAccount(transaction.getTransferAccount());
                tmpWithdrawalAccount = bank.findAccount(transaction.getWithdrawalAccount());
                if (tmpTransferAccount != null && tmpWithdrawalAccount != null) break;
            }

            Objects.requireNonNull(tmpTransferAccount).replenishmentMoney(transaction.getAmount());
            Objects.requireNonNull(tmpWithdrawalAccount).withdrawalMoney(transaction.getAmount());
        } else if (transaction.getWithdrawalAccount() == null && transaction.getTransferAccount() != null) {
            for(Bank bank : banks)
            {
                tmpTransferAccount = bank.findAccount(transaction.getTransferAccount());
                if (tmpTransferAccount != null) break;
            }

            Objects.requireNonNull(tmpTransferAccount).withdrawalMoney(transaction.getAmount());
        } else if (transaction.getTransferAccount() == null && transaction.getWithdrawalAccount() != null) {
            for(Bank bank : banks)
            {
                tmpWithdrawalAccount = bank.findAccount(transaction.getWithdrawalAccount());
                if (tmpWithdrawalAccount != null) break;
            }

            Objects.requireNonNull(tmpWithdrawalAccount).replenishmentMoney(transaction.getAmount());
        }

        transactions.remove(transaction);
    }

    public void manageTime(int countOfDay) {
        for (int i = 0; i < countOfDay % 30; i++) {
            for(Bank bank : banks)
            {
                bank.accruePercentage();
            }
        }
    }

    public boolean findBank(Bank bank) {
        return banks.contains(bank);
    }
}
