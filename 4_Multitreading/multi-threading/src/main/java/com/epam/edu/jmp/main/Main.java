package com.epam.edu.jmp.main;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.Currency;
import com.epam.edu.jmp.model.Customer;
import com.epam.edu.jmp.model.ExchangeRate;
import com.epam.edu.jmp.service.BankService;
import com.epam.edu.jmp.transaction.MoneyExchangeTransaction;
import com.epam.edu.jmp.transaction.MoneyTransferTransaction;
import com.google.common.collect.Lists;

public class Main {

    private static final int RATE_PRECISION = 1_000_000;

    private static final int TRANSACTIONS_NUMBER = 5;

    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    private final static Scanner scanner = new Scanner(System.in);
    private final static Random random = new Random();

    private static List<Bank> banks = Lists.newArrayList();
    private static List<Customer> customers = Lists.newArrayList();

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("src/resources/log4j.properties");

        prepareInitSituation();

        int option = 0;
        do {
            System.out.println("Make your choise:");
            System.out.println("1. Add new Bank.");
            System.out.println("2. Show all banks.");
            System.out.println("3. Show all currencies.");
            System.out.println("4. Add new customer.");
            System.out.println("5. Show all customers.");
            System.out.println("6. Create bank account for user.");
            System.out.println("7. Transfer money (auto simulation).");
            System.out.println("8. Exchange money (auto simulation).");
            System.out.println("9. Capitalization.");

            System.out.println("99. Exit");
            option = scanner.nextInt();
            double before = capitalization();
            double after = capitalization();
            List<Thread> threads = Lists.newArrayList();

            switch (option) {
            case 1:
                addNewBank();
                break;
            case 2:
                showAllBanks();
                break;
            case 3:
                showAllCurrencies();
                break;
            case 4:
                break;
            case 5:
                showAllCustomers();
                break;
            case 6:
                break;
            case 7:
                System.out.println("Banks capitalization before : " + before);
                threads = Lists.newArrayList();
                for (int i = 0; i < TRANSACTIONS_NUMBER; ++i) {
                    threads.add(doTransfer());
                }
                for (Thread thread : threads) {
                    thread.start();
                }
                after = capitalization();
                System.out.println("Banks capitalization after : " + after);
                break;
            case 8:
                System.out.println("Banks capitalization before : " + before);
                threads = Lists.newArrayList();
                for (int i = 0; i < TRANSACTIONS_NUMBER; ++i) {
                    threads.add(doExchange());
                }
                for (Thread thread : threads) {
                    thread.start();
                }
                after = capitalization();
                System.out.println("Banks capitalization after : " + after);
                break;
            case 9:
                System.out.println("Banks capitalization : " + capitalization());
                break;
            }

        } while (option != 99);
    }

    private static double capitalization() throws CurrenciesExchangeIsNotSupportedExceprion {
        double sum = 0;
        for (Bank bank : banks) {
            sum += BankService.capitalization(bank);
        }
        return sum;
    }

    private static Thread doTransfer() {
        Account from = selectAccount();
        Account to = selectAccount();
        while (from.getNumber().equals(to.getNumber())) {
            to = selectAccount();
        }

        double amount = random.nextInt(50);

        Thread transaction = new Thread(new MoneyTransferTransaction(from, to, amount));
        return transaction;
    }

    private static Thread doExchange() {
        Bank bank = selectBank();
        Account from = selectBankAccount(bank);
        Account to = selectBankAccount(bank);
        while (from.getNumber().equals(to.getNumber())) {
            to = selectAccount();
        }

        double amount = random.nextInt(10);

        Thread transaction = new Thread(new MoneyExchangeTransaction(bank, from, to, amount));
        return transaction;
    }

    private static Bank selectBank() {
        return banks.get(random.nextInt(banks.size()));
    }

    private static Account selectBankAccount(Bank bank) {
        return bank.getAccounts().get(random.nextInt(bank.getAccounts().size()));
    }

    private static Account selectAccount() {
        List<Account> accounts = Lists.newArrayList();
        for (Bank bank : banks) {
            accounts.addAll(bank.getAccounts());
        }
        return accounts.get(random.nextInt(accounts.size()));
    }

    private static void showAllCustomers() {
        System.out.println("Customers list contains " + customers.size()
                + " values. Here they are...");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void showAllCurrencies() {
        System.out.println("Currencies list contains "
                + Currency.values().length + " values. Here they are...");
        for (Currency currency : Currency.values()) {
            System.out.println(currency);
        }
    }

    private static void showAllBanks() {
        System.out.println("Banks list contains " + banks.size()
                + " banks. Here they are...");
        for (Bank bank : banks) {
            System.out.println(bank);
        }
    }

    private static void addNewBank() {
        System.out.println("Adding new Bank...");
        String bankName;
        System.out.println("Give BANK name:");
        scanner.nextLine();
        bankName = scanner.nextLine();
        int bankCode;
        System.out.println("Give BANK code:");
        bankCode = scanner.nextInt();
        Bank bank = new Bank(bankCode, bankName);
        banks.add(bank);
        System.out.println(bank.toString() + " bank was added.");
    }

    private static void prepareInitSituation() {
        Bank vtb = new Bank(31, "VTB");
        Bank mtBank = new Bank(45, "MTBank");
        Bank prior = new Bank(14, "PriorBank");

        banks.addAll(Lists.newArrayList(vtb, mtBank, prior));
        Customer ivan = new Customer("Ivan", "Ivankov");

        for(int i = 0; i < 15; ++i) {
            Bank bank = banks.get(random.nextInt(banks.size()));
            Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
            int amount = random.nextInt(500);
            Account account = bank.openNewAccount(currency, amount);
            ivan.addAccount(account);
        }

        List<ExchangeRate> rates = Lists.newArrayList();
        for(int i = 0; i < Currency.values().length; ++i) {
            rates.add(new ExchangeRate(Currency.values()[i], Currency.values()[i], 1));
            for (int j = i + 1; j < Currency.values().length; ++j) {
                double rate = Math.round(random.nextDouble() * RATE_PRECISION) / (double) RATE_PRECISION;
                rates.add(new ExchangeRate(Currency.values()[i], Currency.values()[j], rate));
                rates.add(new ExchangeRate(Currency.values()[j], Currency.values()[i], 1 / rate));
            }
        }

        for (Bank bank : banks) {
            bank.setRates(rates);
        }

        customers.add(ivan);

    }

}
