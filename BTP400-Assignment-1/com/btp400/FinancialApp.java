package com.btp400;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.seneca.accounts.*;
import com.seneca.business.Bank;
import com.seneca.exceptions.AccountExceptions;



public class FinancialApp {
	
	
	private static Bank m_Bank;
	
	/**
	 * 
	 * @param Bank Name
	 */
	public static void main(String args[]) {
	
		//Flag for checking if the user is done using the app
		boolean userDone = false;
		
		//Variable representing the user's menu choice
		int userChoice = 0;
		
		//Initialization of m_Bank 
		if (args != null) {
			loadBank(new Bank(args[0]));
		}else {
			loadBank(new Bank());	
		}
		
		
		//Start main program
		do {
			
			DisplayMenu();
			userChoice = menuChoice();
			//System.out.println(userChoice);
			
			switch(userChoice) {
			
			case 1:
				openAccount(); 
				break;
			case 2:
				closeAccount();
				break;
			case 3:
				depositMoney();
				break;
			case 4 :
				withdrawMoney();
				break;
			case 5: 
				DisplayAccountsMenu();
				break;
			case 6:
				DisplayTaxStatement();
				break;
			case 7:
				System.out.println("\nExiting...");
				userDone = true;
				break;
			}
			
			
		} while(userDone == false);
		
		
		
		
	}
	
	
/**
 * Displays all TaxStatements belonging to the same account holder's name
 */
	private static void DisplayTaxStatement() {
		
		

		Account[] accounts = m_Bank.searchByAccountName(getUserInputString("account holder's name"));
		StringBuffer count = new StringBuffer("\n[");
		
		
		
		
		Taxable taxAcc = null; //Used to get access to the taxable's accounts createTaxStatements function
		int i = 1; //counter
		
		System.out.println();
		
		if (accounts.length > 0) {
			
			
			System.out.println(new StringBuffer(accounts[0].getLastName()).append(", ").append(accounts[0].getFirstName()));
			System.out.println(new StringBuffer("Tax Rate: ").append(String.format("%.0f",Taxable.TAX.multiply(new BigDecimal("100")).doubleValue())).append("%"));
		
			for (Account acc : accounts) {
				
				if (acc instanceof Taxable) {
					taxAcc = (Taxable) acc;
					
					System.out.println(new StringBuffer(count).append(i++).append( "]"));
					System.out.println(new StringBuffer(taxAcc.createTaxStatement()).append("\n\n"));
					
				}
				
			}
		}else {
			System.out.println("\nNo one has an account with that name\n");
		}
	}


	/**
	 * Deposits money into a specific bank account
	 */
	private static void depositMoney() {
		
		
		System.out.println("\n---Deposit---");
		
		StringBuffer errMsg = new StringBuffer("\nSomething went wrong with your transaction : \n" );
		Account[] bankAccounts = m_Bank.getAllAccounts();
		
		String userAccountNumber = getUserInputString("account number");
		
		boolean accFound = false;
		double despotAmount = getUserInputMoneyAmount();
		
		
		
		for (Account acc : bankAccounts) {
			
			if (acc.getAccountNumber().equals(userAccountNumber)) {
				try {
					accFound = true;
					acc.deposit(despotAmount);
					System.out.println("\nMoney sucessfully deposited.\n");
					
				}
				catch(AccountExceptions e) {
					//System.out.println("TEST");
					System.out.println(errMsg.append(e).append("\n"));
				}
				
			}
			
		}
		
		if (accFound == false) {
			System.out.println("\nThat account does not exist \n");
			
		}
			
	}
	
	
	
	
	
	

	/**
	 * withdraws money from a specific bank account
	 */		
		private static void withdrawMoney() {
			
			
			System.out.println("\n---Withdraw---");
			
			StringBuffer errMsg = new StringBuffer("Something went wrong with your transaction : \n" );
			Account[] bankAccounts = m_Bank.getAllAccounts();
			
			String userAccountNumber = getUserInputString("account number");;
			boolean accFound = false;
			double withdrawAmount = getUserInputMoneyAmount();
			
			
			
			for (Account acc : bankAccounts) {
				
				if (acc.getAccountNumber().equals(userAccountNumber)) {
					try {
						
						accFound = true;
						if (acc.withdraw(withdrawAmount)) {
							System.out.println("\nMoney sucessfully withdrawn.\n");
						}
						else {
							System.out.println("\nMoney withdrawal failed.\n");
						}
						
					}
					catch(AccountExceptions e) {
						System.out.println(errMsg.append(e).append("\n"));
					}
					
				}
				
			}
		
			if (accFound == false) {
				System.out.println("\nThat account does not exist.. \n");
				
			}
		
		
	}

		
		/**
		 * function that displays sub-options for display account option
		 */
		private static void DisplayAccountsMenu() {
			
			Scanner userInput = new Scanner(System.in);
			boolean valid_input = false;
			
			System.out.println("\na) Display all accounts with the same name\nb) Display all accounts with the same final balance\nc) Display all accounts opened at the bank\n");
			
			do {
				
				
				switch(userInput.nextLine()) {
				
				
					case "a" :
						DisplayAccounts(1);
						valid_input = true;
						break;
					case "b" : 
						DisplayAccounts(2);
						valid_input = true;
						break;
					case "c" :
						DisplayAccounts(3);
						valid_input = true;
						break;
					default : 
						System.out.println("\nInvalid input. Try Again\n");
					    break;
					
				
				}
				
				
			}while(valid_input == false);
			
			
		}
		
		
		/**
		 * Displays a collection of accounts based on the user's choice
		 * @param option represents the user choice( 1 = matching account name, 2 = matching balance, 3 = all)
		 */
		private static void DisplayAccounts(int option) {
			
			Scanner userInput = new Scanner(System.in);
			
			StringBuffer prompt = new StringBuffer();
			Account[] accounts = null;
			
			
			if (option == 1) {
				
				System.out.println(prompt.append("Please enter Name"));
				accounts = m_Bank.searchByAccountName(userInput.nextLine());
		
			}
			else if(option == 2) {
				
				accounts = m_Bank.searchByBalance(getUserInputMoneyAmount());
			}
			else if (option == 3) {
				accounts = m_Bank.getAllAccounts();
			}
			
			
			if (accounts != null) {
				if (accounts.length > 0) {
					
					for (int i = 0; i < accounts.length; ++i) {

						System.out.println(new StringBuffer("\n[").append((i+1)).append("]"));
						System.out.println(accounts[i] + "\n");
						
					}
					
				}
				else {
					System.out.println("\nYour queury returned empty\n");
				}
			}
			else {
				System.out.println("\nSomething unexpected went wrong \n");
			}
		
			
			
		}


		
	/**
	 * initializes the m_Bank variable and adds 4 default accounts to it
	 * @param bank the bank object that the financial app will reference throughout it's functionality
	 */
	public static void loadBank(Bank bank) {
		if (bank != null) {
			m_Bank = bank;
			
			
			m_Bank.addAccount(new GIC("John Doe","N100",6000,2,1.5));
			m_Bank.addAccount(new GIC("Mary Ryan","N101",15000,4,2.5));
			
			m_Bank.addAccount(new Chequing("John Doe","N102",6000,-1,-1));
			m_Bank.addAccount(new Chequing("Mary Ryan","N103",15000,-1,-1));
		}
		
		
		
		
	}
	
	/**
	 * prints out the main options that the user can choose from
	 */
	public static void DisplayMenu() {
		
		StringBuffer menu = new StringBuffer("\nWelcome to ").append(m_Bank.getBank_Name()).append(" Bank!\n");
		menu.append("1. Open an account.\n");
		menu.append("2. Close an account.\n");
		menu.append("3. Deposit money.\n");
		menu.append("4. Withdraw money.\n");
		menu.append("5. Display accounts.\n");
		menu.append("6. Display a tax statement.\n");
		menu.append("7. Exit.");
		
	
	
		String msg_accountInfo = "Please enter account information at one line";
		
		System.out.println(menu);
		
		
	}
	
	/**
	 * gets the users input for main menu choice
	 * @return users choice
	 */
	public static int menuChoice() {
		
		
		int int_userInput = -1;
		boolean valid_input = true;
		Scanner userInput = new Scanner(System.in);
		
		do {
			
			System.out.println("\nPlease enter your choice : ");
			
			
			try {
				
				
				int_userInput = userInput.nextInt();
			
				
				if (!(int_userInput >= 1 && int_userInput <=7)) {
					valid_input = false;
					System.out.println("\nThat is not a valid numbered option.\n");
				}
				else {
					valid_input =true;
				}
			}catch(InputMismatchException e) {
				
				valid_input = false;
				System.out.println("\nThat was not a valid input. Try Again.\n");
				
				
			}catch(Exception e) {
				valid_input = false;
				System.out.println("\nOops.. Something unexpected happended. Try Again.\n");
			}
			
			
			
		
			
			userInput.nextLine(); //consume \n after nextInt is called.
		} while(valid_input == false);
		
		
		
		return int_userInput;
		
		
		
	}
	
	
	/**
	 * Get's user input and converts it to a  valid double
	 * @return the amount of money the user wants to do [Action] on
	 */
	public static double getUserInputMoneyAmount(){
		
		double double_userInput = 0;
		boolean valid_input = false;
		Scanner userInput = new Scanner(System.in);
		
		
		
		
		String str_userInput = null;
	
		
		
		do {
			
			System.out.println("Please enter amount:");
			str_userInput = userInput.nextLine();
			
			
			try {
				
				double_userInput = Double.parseDouble(str_userInput);
				valid_input = true;
				
			}catch(NumberFormatException e) {
				System.out.println("\nThere was a type mismatch in one of your fields \n");
			}
			catch(Exception e) {
				System.out.println(new StringBuffer("\nThere was an unexpected error in your input. Please send this error to devs and try again.").append(e).append("\n"));
			}
			
		
		} while(valid_input == false);
		
		
		return double_userInput;
		
	}
	
	/**
	 * gets user input for a valid account type
	 * @return String representing valid account type
	 */
	public static String getUserInputAccountType(){

		Scanner userInput = new Scanner(System.in);
		String str_userInput;
		boolean valid_accType = false;
	
		
		do {
			System.out.println("\nPlease enter the account type (CHQ/GIC) : ");
			str_userInput = userInput.nextLine();
			System.out.println();
			
			if (str_userInput.equals("GIC") || (str_userInput.equals("CHQ"))){
				valid_accType = true;
			}
			else {
				System.out.println("\nInvalid account type. Try again..\n");
			}
		
		} while(valid_accType == false);
		
		return str_userInput;
	}
	
	
	/**
	 * flexible getInput function for user input
	 * @param field the name of the field that you want to ask the user to input.
	 * @return a string representing the user's input
	 */
	public static String getUserInputString(String field){

		Scanner userInput = new Scanner(System.in);
		String str_userInput;
				
			
	
			System.out.println(new StringBuffer("\nPlease enter ").append(field).append(" :"));
			str_userInput = userInput.nextLine();
	
		
		return str_userInput;
	}
	
	
	
	
	public static String[] getUserInputAccountData() {
		
		Scanner userInput = new Scanner(System.in);
		String[] accData = null;
		boolean valid_userInput = false;
		
		
		do {
			System.out.println("Please enter account information at one line\r\n" + 
					"(e.g. John M. Doe;A1234;1000.00;1.5;2)\r\n");
			try {
			
				accData = userInput.nextLine().split("\\s*;\\s*");
				valid_userInput = true;
			}
			catch(Exception e) {
				System.out.println("\n Something went wrong. Please try again. \n");
			}
			
		}while(valid_userInput == false);
		
		return accData;
	}
	
	/**
	 * Converts user input string into a valid account object
	 * @return account object based on user input
	 */
	public static Account produceAccountFromUser() { //Produces an account object from user's inputs
		
	boolean valid_accData = false;
	String[] accData = null;	
	Account acc = null;
	
	String accType = getUserInputAccountType();
		
		
		do {
			
		
			
			accData = getUserInputAccountData();
			
			if (accData.length == 5) {
				
				if (accType.equals("GIC")) {
				
						
						try {
							
							acc = new GIC(accData[0], accData[1], Double.parseDouble(accData[2]), Integer.parseInt(accData[4]), Double.parseDouble(accData[3]));
							valid_accData = true;
							
						}
						catch(NumberFormatException e) {
							System.out.println("\nThere was a type mismatch in one of your fields \n");
						}
						catch(Exception e) {
							System.out.println(new StringBuffer("\nThere was an unexpected error in your input. Please send this error to devs and try again: ").append(e).append("\n"));
						}
					
						
				}
				else if (accType.equals("CHQ")) {
					try {
					
					acc = new Chequing(accData[0], accData[1], Double.parseDouble(accData[2]),Double.parseDouble(accData[3]),Integer.parseInt(accData[4]));
					valid_accData = true;
					
					}
					catch(NumberFormatException e) {
						System.out.println("\nThere was a type mismatch in one of your fields \n");
					}
					catch(Exception e) {
						System.out.println(new StringBuffer("\nThere was an unexpected error in your input. Please send this error to devs and try again:").append(e).append("\n"));
					}
				}
					
				
				
	
			}
			else {
				System.out.println("\nThe GIC & CHQ account only accepts 5 parameters. Please try again.\n");
			}
		} while(valid_accData == false);
		
		return acc;
	}
	
	

	/**
	 * Closes account based on account number.
	 */
	private static void closeAccount() { 
		
		Account acc = null;
		Scanner userInput = new Scanner(System.in);
		
		
		System.out.println("Please enter account number :  ");
		
		acc = m_Bank.removeAccount(userInput.nextLine());
		
		if (acc != null) {
			System.out.println(new StringBuffer("\nAccount sucessfully deleted:\n").append(acc.toString()));
		}
		else {
			System.out.println("\nAccount does not exist\n");
		}
		
		
	}

	
	/**
	 * Opens new account in the bank using user input
	 */
	public static void openAccount() {
		

		Account acc = produceAccountFromUser();
	
		
		if (m_Bank.addAccount(acc)) {
			System.out.println(new StringBuffer("\nAccount Sucessfully Added : \n\n").append( acc.toString()));
		}
		else{
			System.out.println("\nOops, it looks like that account may already exist or you sent us a null account.\n");
		}
		
		
	}
	
}
