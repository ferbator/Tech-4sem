package tools;

import accountServices.AccountOption;
import accountServices.DepositAccountPercentage;
import accountServices.IAccount;
import banksServices.Bank;
import banksServices.CentralBank;
import banksServices.Transaction;
import clientServices.Client;

import java.util.Scanner;

public class ConsoleInterface {
    public static void input() throws CentralBankException {
        var centralBank = new CentralBank();
        Client client = null;
        IAccount account1 = null;
        IAccount account2 = null;
        Bank bank = null;
        Transaction transaction = null;
        String b = null;
        while (b != "Q")
        {
            System.out.println("1 - Start Create Bank");
            System.out.println("2 - Start Create Client");
            System.out.println("3 - Start Create Account");
            System.out.println("4 - Start Do Transaction");
            System.out.println("5 - Start Canceled Transaction");
            System.out.println("Q - Exit Program");
            Scanner in = new Scanner(System.in);
            b = in.nextLine();

            switch (b)
            {
                case "1":
                {
                    System.out.println("Set Name\nExample: string");
                    String name = in.nextLine();
                    System.out.println("Set limitForNotVerification\nExample: double");
                    double limitForNotVerification = in.nextDouble();
                    System.out.println("Set creditLimitForCreditAccounts\nExample: double");
                    double creditLimitForCreditAccounts = in.nextDouble();
                    System.out.println("Set commissionUsingForCreditAccounts\nExample: double");
                    double commissionUsingForCreditAccounts = in.nextDouble();
                    System.out.println("Set percentageOnBalanceForDepositAccounts\nExample: 50000 3\nExample: 100000 5\nExample:1000000 6");
                    DepositAccountPercentage percentageOnBalanceForDepositAccounts = new DepositAccountPercentage();
                    for (int i = 0; i < 3; i++)
                    {
                        Scanner in2 = new Scanner(System.in);
                        String str = in2.nextLine();
                        double first = Double.parseDouble(str.split(" ")[0]);
                        double second = Double.parseDouble(str.split(" ")[1]);
                        percentageOnBalanceForDepositAccounts.addParametersForDepositAccountBank(first, second);
                    }

                    System.out.println("Set percentageOnBalanceForDebitAccounts\nExample: double");
                    double percentageOnBalanceForDebitAccounts = in.nextDouble();
                    bank = centralBank.addBankToBase(
                            name,
                            limitForNotVerification,
                            creditLimitForCreditAccounts,
                            commissionUsingForCreditAccounts,
                            percentageOnBalanceForDepositAccounts,
                            percentageOnBalanceForDebitAccounts);
                    System.out.println("Done");
                    break;
                }

                case "2":
                    System.out.println("1 - Start Create Client");
                    System.out.println("2 - Start Create Verification Client");
                    b = in.nextLine();
                    switch (b)
                    {
                        case "1":
                        {
                            System.out.println("Set name\nExample: string");
                            String name = in.nextLine();
                            System.out.println("Set surname\nExample: string");
                            String surname = in.nextLine();
                            client = Client.Builder(name, surname).getClient();
                            System.out.println("Done");
                            break;
                        }

                        case "2":
                        {
                            System.out.println("Set name\nExample: string");
                            String name = in.nextLine();
                            System.out.println("Set surname\nExample: string");
                            String surname = in.nextLine();
                            System.out.println("Set address\nExample: string");
                            String address = in.nextLine();
                            System.out.println("Set passport\nExample: string");
                            String passport = in.nextLine();
                            client = Client.Builder(name, surname).addAddress(address).addPassport(passport).getClient();
                            System.out.println("Done");
                            break;
                        }
                    }

                    break;
                case "3":
                    System.out.println("1 - Start Create Debit");
                    System.out.println("2 - Start Create Credit");
                    System.out.println("3 - Start Create Deposit");
                    b = in.nextLine();
                    switch (b)
                    {
                        case "1":
                            account1 = centralBank.regAccountClientInBank(bank, client, AccountOption.Debit, 10000);
                            account2 = centralBank.regAccountClientInBank(bank, client, AccountOption.Debit, 10000);
                            System.out.println("Done");
                            break;
                        case "2":
                            account1 = centralBank.regAccountClientInBank(bank, client, AccountOption.Credit, 10000);
                            account2 = centralBank.regAccountClientInBank(bank, client, AccountOption.Credit, 10000);
                            System.out.println("Done");
                            break;
                        case "3":
                            account1 = centralBank.regAccountClientInBank(bank, client, AccountOption.Deposit, 10000);
                            account2 = centralBank.regAccountClientInBank(bank, client, AccountOption.Deposit, 10000);
                            System.out.println("Done");
                            break;
                    }

                    break;
                case "4":
                    System.out.println("Set Amount Transaction\nExample: double");
                    double amount = in.nextDouble();
                    System.out.println("1 - Do Replenishment Money Transaction");
                    System.out.println("2 - Do Withdrawal Money Transaction");
                    System.out.println("3 - Do Transfer Money Transaction");
                    b = in.nextLine();
                    switch (b)
                    {
                        case "1":
                            transaction = centralBank.replenishmentMoney(account1, amount);
                            System.out.println("Done");
                            break;
                        case "2":
                            transaction = centralBank.withdrawalMoney(account1, amount);
                            System.out.println("Done");
                            break;
                        case "3":
                            transaction = centralBank.transferMoney(account1, account2, amount);
                            System.out.println("Done");
                            break;
                    }

                    break;
                case "5":
                    centralBank.cancelTransaction(transaction);
                    System.out.println("Done");
                    break;
            }
        }
    }
}
