package com.seneca.business;

import java.util.ArrayList;
import java.util.Objects;

import com.seneca.accounts.Account;

/**
 * The Bank class handles a collection of Account objects with CRUD operations.
 * @author Rennie
 * @since 2020-02-17
 */

public class Bank {

	/**
	 * The Bank's name
	 */
	private String m_Bank_Name;
	
	/**
	 * Collection of accounts that the bank handles
	 */
	private ArrayList<Account> m_Accounts;
	
	/**
	 * Default constructor that sets the bank's name to Seneca@York
	 */
	public Bank() {
		this("Seneca@York");
	}
	
	
	/**
	 * 
	 * @param Represents the name that you would like to set the bank too
	 */
	public Bank(String name) {
		
		
		if (name == null || name == "")
			name = "Seneca@York";
		m_Bank_Name = name;
		
		m_Accounts = new ArrayList<Account>();
		
	}
	
	
	/**
	 * 
	 * @return The name of the bank
	 */
	public String getBank_Name() {
		return m_Bank_Name;
	}

	/**
	 * 
	 * @return All accounts that the bank currently handles
	 */
	public ArrayList<Account> getAccounts() {
		return m_Accounts;
	}
	
	/**
	 * Add an account to the bank
	 * @param newAccount The account object you would like to add to the bank.  Account's account Number must be unique or else it will return false
	 * @return a boolean describing whether the account was sucessfully added or not
	 */
	public boolean addAccount(Account newAccount) {
		
		boolean accAdded = false; //assume an account wont be added until proven otherwise
		boolean found = false;
		
		if (newAccount != null && !findAccount(newAccount) ) { //if no existing account matches newAccount then add it
			
			for(Account acc : m_Accounts) {
				
				if(newAccount.getAccountNumber().equals(acc.getAccountNumber()))
					found = true;
			}
			
			if(found == false) {
				m_Accounts.add(newAccount);
				accAdded = true;  //when successfully add trigger accAdded flag
			}
		}
		return accAdded; //return accAdded value
	}
	
	
	/**
	 * Remove an account from the bank
	 * @param accountNumber The account number of the account you want to delete
	 * @return Account object that was deleted. null if no account was deleted.
	 */
	public Account removeAccount(String accountNumber) {
		
		Account acc = null;
		
		
		for (int i = 0; i < m_Accounts.size(); ++i) {
			if (m_Accounts.get(i).getAccountNumber().equals(accountNumber)) {
						
				acc = m_Accounts.remove(i);
						
			}
	
		}
		
		return acc;
	}
	
	
	/**
	 * Search for accounts with the same balance
	 * @param bal The balance you would like to use for the query
	 * @return All accounts that match query. Returns an empty array if no accounts were found'
	 */
	public Account[] searchByBalance(double bal) {
		
		ArrayList<Account> matchedAccounts = new ArrayList<Account>();
		Account[] accs;
			
		for(Account acc : m_Accounts) {
		
			if (acc.getBalance() == bal) {
				matchedAccounts.add(acc);
			}
		}
		accs = matchedAccounts.toArray(new Account[matchedAccounts.size()]);

		return  accs;
	}
	
	/**
	 * Checks if an account already exist in bank
	 * @param queryAcc Account you like to use for the query
	 * @return a boolean if that account exist in the bank 
	 */
	public boolean findAccount(Account queryAcc) {
		
		boolean accFound = false; //Assume no account matches query account
		
		if (queryAcc != null) {
			
			for (Account acc : m_Accounts) { //search m_Accounts arrayList to find matching account
				if (queryAcc.equals(acc)) { 
					accFound = true;  //if found then set accFound flag to true and break for loop;
					break;
				}
			}			
		}
		return accFound; //return accFound flag value;	
	}
	

	/**
	 * Get all accounts that matches a person's full name
	 * @param accountName
	 * @return  All accounts that match query. Returns an empty array if no accounts were found'
	 */
	public Account[] searchByAccountName(String accountName) {

		ArrayList<Account> accsFound = new ArrayList<>(); 
		
		if (accountName != null) {
			
			for (Account acc : m_Accounts) { //search m_Accounts arrayList to find matching account
				if (acc.getFullName().equals(accountName)) {
					
					accsFound.add(acc);
				}
			}
		}
		return accsFound.toArray(new Account[accsFound.size()]); //return accFound flag value;
	}
	
	/**
	 * 
	 * @return all accounts in the bank's collection
	 */
	public Account[] getAllAccounts() {

		return m_Accounts.toArray(new Account[m_Accounts.size()]);
	}
	
	public String toString() {
		
		int counter = 0;
		

		
		StringBuffer str = new StringBuffer( "*** Welcome to the Bank of ").append(getBank_Name()).append( "***\n").append("It has ").append(m_Accounts.size()).append(" accounts.\n"); 
		
		
		for (Account acc : this.m_Accounts) {
			
			str.append(++counter).append(". number: ").append(acc.getAccountNumber()).append(" name: ").append(acc.getFullName()).append(" balance: $").append(String.format("%.2f",acc.getBalance())).append("\n");
		}
		
		str.append("\n");
		
		return str.toString();
	}
	
	public int hashCode() {
		return Objects.hash(m_Accounts, m_Bank_Name);
	}
	
	
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof Bank))
			return false;
		
		Bank other = (Bank) obj;
		
		return Objects.equals(m_Accounts, other.m_Accounts) && 
			   Objects.equals(m_Bank_Name, other.m_Bank_Name);
	}
}