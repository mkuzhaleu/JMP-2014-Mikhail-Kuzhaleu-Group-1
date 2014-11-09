package com.epam.edu.jmp.main;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.Currency;
import com.epam.edu.jmp.model.Customer;
import com.epam.edu.jmp.service.BankService;
import com.epam.edu.jmp.service.Transaction;
import com.google.common.collect.Lists;

public class Main {
	
	private final static Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private final static Scanner scanner = new Scanner(System.in);
	private final static Random random = new Random();
	
	private static List<Bank> banks = Lists.newArrayList();
	private static List<Customer> customers = Lists.newArrayList();

    public static void main( String[] args ) throws Exception {
    	
    	prepareInitSituation();

        int option = 0;
        do {
            System.out.println( "Make your choise:" );
            System.out.println( "1. Add new Bank." );            
            System.out.println( "2. Show all banks." );
            System.out.println( "3. Add currency." );
            System.out.println( "4. Show all currencies." );
            System.out.println( "5. Add new customer." );
            System.out.println( "6. Show all customers." );
            System.out.println( "7. Create bank account for user." );
            System.out.println( "8. Transfer money." );
            System.out.println( "9. Capitalization." );
            
            System.out.println( "99. Exit" );
            option = scanner.nextInt();
            switch (option) {
            case 1:
            	addNewBank();
            	break;
            case 2:
            	showAllBanks();
            	break;
            case 3:
            	break;
            case 4:
            	showAllCurrencies();
            	break;
            case 5:
            	break;
            case 6:
            	showAllCustomers();
            	break;
            case 7:
            	break;
            case 8:
            	double before = capitalization();
            	System.out.println("Banks capitalization before : " + before);
            	List<Thread> threads = Lists.newArrayList();
            	for(int i = 0; i < 100; ++i) {
            		System.out.println(i);
            		threads.add(doTransfer());
            	}
            	for(Thread thread : threads) {
            		thread.start();
            	}
            	double after = capitalization();
            	System.out.println("Banks capitalization after : " + after);
            	break;
            case 9:
            	System.out.println("Banks capitalization : " + capitalization());
            	break;
            case 10:
            	break;
            case 11:
            	break;
            }

        } while (option  != 99);
    }

	private static double capitalization() {
		double sum = 0;
		for(Bank bank : banks) {
			sum += BankService.capitalization(bank);
		}
		return sum;
	}

	private static Thread doTransfer() {
		System.out.println("Transfering money service");
		Account from = selectAccount();
		Account to = selectAccount();
		while (from.getNumber().equals(to.getNumber())) {
			to = selectAccount();
		}

		double amount = random.nextInt(50);
		
		Thread transaction = new Thread(new Transaction(from, to, from.getCurrency(), amount));
		return transaction;
	}
	
	private static Account selectAccount() {
		List<Account> accounts = Lists.newArrayList();
		for(Bank bank : banks) {
			accounts.addAll(bank.getAccounts());
		}
		return accounts.get(random.nextInt(accounts.size()));
	}

	private static void showAllCustomers() {
		System.out.println("Customers list contains " + customers.size()  + " values. Here they are...");
		for (Customer customer : customers) {
			System.out.println(customer);
		}	
	}

	private static void showAllCurrencies() {
		System.out.println("Currencies list contains " + Currency.values().length  + " values. Here they are...");
		for (Currency currency : Currency.values()) {
			System.out.println(currency);
		}
	}

	private static void showAllBanks() {
		System.out.println("Banks list contains " + banks.size()  + " banks. Here they are...");
		for (Bank bank : banks) {
			System.out.println(bank);
		}
	}

	private static void addNewBank() {
		System.out.println( "Adding new Bank..." );
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
		
		Account acc1 = vtb.openNewAccount(Currency.USD, 100);
		Account acc2 = mtBank.openNewAccount(Currency.USD, 50);
		Account acc3 = prior.openNewAccount(Currency.USD, 250);
		
		Customer ivan = new Customer("Ivan", "Ivankov");
		
		ivan.addAccount(acc1);
		ivan.addAccount(acc2);
		ivan.addAccount(acc3);
		
		customers.add(ivan);
		banks.addAll(Lists.newArrayList(vtb, mtBank, prior));
	}

}
