package com.epam.edu.jmp.main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.Currency;
import com.epam.edu.jmp.model.Customer;
import com.google.common.collect.Lists;

public class Main {
	
	private final static Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private final static Scanner scanner = new Scanner(System.in);
	
	private static List<Bank> banks = Lists.newArrayList();
	private static List<Customer> customers;

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
            System.out.println( "7. Add bank account for user." );
            System.out.println( "8. Transfer money." );
            
            System.out.println( "9. Exit" );
            option = scanner.nextInt();

        } while (option  != 7);
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
