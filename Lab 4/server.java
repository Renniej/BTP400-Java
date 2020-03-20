import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.seneca.accounts.Account;
import com.seneca.accounts.Chequing;
import com.seneca.accounts.GIC;
import com.seneca.exceptions.AccountExceptions;

import business.Bank;


public class server {
	
	
	private static Bank m_Bank;
	private static ServerSocket ss;
	private static Socket s;
	private static Account rec_acc; //Account recieved from clinet
	private static Account sent_acc; //Account being sent to client
	private static ObjectInputStream objInput;
	private static ObjectOutputStream objOutput;
	private static String cmd ="";
	private static double amount;
	private static String errMsg = null;
	private static boolean transactionError = false;
	private static int responseCode = 0;  // 0 = invalid request, 1 = valid request
	
	
	public static void main(String[] args) throws IOException {
		
		
		loadBank(new Bank("Bank@Seneca”"));
		
		System.out.println("Welcome to " + m_Bank.getBank_Name() + "!");
		System.out.println("Waiting on client connection...");
		
		
		ss = new ServerSocket(8000);
		s = ss.accept();
		
		rec_acc = null;
		sent_acc = null;
		String user_input = "";
	
		
		objInput = new ObjectInputStream(s.getInputStream());
		objOutput = new ObjectOutputStream( s.getOutputStream());
		
		
		
		
		System.out.println("Client Connected");
		
		
		while (!user_input.equals("quit")) {
			
			user_input = "";
			//reset variables
			transactionError = false;
			rec_acc = null;
			responseCode = 0;
			sent_acc = null;
			
			try {
				
				user_input = (String) objInput.readObject(); //2
				
				if (!validCommand(user_input)) { //3
					
					System.out.println("Invalid string command sent");
					setReponseCode(0); //invalid command
				}
				else if (user_input.equals("quit")) {
					
					System.out.println("Client has requested to disconnect");
					continue;
					
				}
				else {
					
					setReponseCode(1); //valid command
					rec_acc = (Account) objInput.readObject(); //6
					
					if (rec_acc == null) {
						System.out.println("an invalid account object was recieved from client");
						setReponseCode(0);
						
						
			
						
					}else {
						setReponseCode(1); //valid account was sent  //7
						
						if (server.cmd.equals("deposit")) {
							
							sent_acc = depositMoney(rec_acc.getAccountNumber(), server.amount);
						
						}
						else if (server.cmd.equals("withdraw")) {
							sent_acc = withdrawMoney(rec_acc.getAccountNumber(), server.amount);
						}
						
						
						if (transactionError == false) {
							
							setReponseCode(1);
							System.out.println(sent_acc);
							objOutput.writeObject(sent_acc);
							objOutput.reset();
							objOutput.flush();
						}
						else {
							setReponseCode(0);
							System.out.println("Something went wrong in the transaction");
							
						}
						
						
					
					}
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
			
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch(EOFException e) {
				System.out.println("Client has ended it's connection");
			}
			 
			
			
		}
		
			System.out.println("Server closing...");
			objInput.close();
			objOutput.close();
			s.close();
			System.out.println("Server Closed.");
	}
	
	
	
		
	
	private static void setReponseCode(int code) {
		responseCode = code;
		
		try {
			objOutput.writeObject(responseCode);
			objOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static boolean validCommand(String command) {
		
		
		boolean isValid = false;
		String cmd = null;
		String params = null;
		
		
		StringTokenizer tokenizer = new StringTokenizer(command);
		
		if (tokenizer.hasMoreElements()) { 
			
			if (tokenizer.countTokens() == 1) {
				if (tokenizer.nextToken().equals("quit")) {
					isValid = true;
				}
				
			}
			else if (tokenizer.countTokens() == 2) {
				
				cmd = tokenizer.nextToken();
				params = tokenizer.nextToken();
				
				if (cmd.equals("withdraw") || cmd.equals("deposit")) {
					
					server.cmd = cmd;
					
					try {
						server.amount = Double.parseDouble(params);
						isValid = true;
					}catch(NumberFormatException e) {
						
					}
					
					
				}
				
				
			}
			
		}
			
		
		
		
		
		return isValid;
	
		
	}
	
	
	
	private static Account withdrawMoney(String userAccountNumber, double withdrawAmount) {
		
		
		System.out.println("\n---Withdraw---");
		Account acct = null;
		StringBuffer errMsg = new StringBuffer("Something went wrong with your transaction : \n" );
		Account[] bankAccounts = m_Bank.getAllAccounts();
		
		
		boolean accFound = false;

		
		
		
		for (Account acc : bankAccounts) {
			
			if (acc.getAccountNumber().equals(userAccountNumber)) {
				try {
					
					accFound = true;
					acct = acc;
					
					if (acc.withdraw(withdrawAmount)) {
						System.out.println("Money sucessfully withdrawn.");
					}
					else {
						transactionError = true;
						System.out.println("Money withdrawal failed.");
					}
					
				}
				catch(AccountExceptions e) {
					transactionError = true;
					System.out.println(errMsg.append(e));
				}
				
			}
			
		}
	
		if (accFound == false) {
			transactionError = true;
			System.out.println("That account does not exist.. good bye");
			
		}
	
	
		return acct;
}

	
private static Account depositMoney(String userAccountNumber, double despotAmount) {
		
		
		System.out.println("\n---Deposit---");
		
		StringBuffer errMsg = new StringBuffer("Something went wrong with clent's transaction : \n" );
		Account[] bankAccounts = m_Bank.getAllAccounts();
		
		
		
		Account accFound = null;
		
		
		
		
		for (Account acc : bankAccounts) {
			
			if (acc.getAccountNumber().equals(userAccountNumber)) {
				try {
					accFound = acc;
					acc.deposit(despotAmount);
					System.out.println("Money sucessfully deposited to client's account.");
					
				}
				catch(AccountExceptions e) {
					transactionError = true;
					//System.out.println("TEST");
					System.out.println(errMsg.append(e));
				}
				
			}
			
		}
		
		if (accFound == null) {
			transactionError = true;
			System.out.println("That account does not exist in server's bank");
			
		}
		
		return accFound;
			
	}
	
	public static void loadBank(Bank bank) {
		if (bank != null) {
			m_Bank = bank;
			
			
			m_Bank.addAccount(new Account("Tai-Juan Rennie","A1234",1000));
			m_Bank.addAccount(new GIC("Mary Ryan","N101",15000,4,2.5));
			
			m_Bank.addAccount(new Chequing("John Doe","N102",6000,-1,-1));
			m_Bank.addAccount(new Chequing("Mary Ryan","N103",15000,-1,-1));
		}
		


	}
	
}
	
	