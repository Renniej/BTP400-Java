package com.seneca.accounts;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import com.seneca.exceptions.*;

/**
 * 
 * @author troberts10
 * @date 17-Feb-2020
 * @version 1.0
 *
 * Chequing Class
 * 
 */
public class Chequing extends Account {

	private BigDecimal m_serviceFee;
	private BigDecimal[] m_transactionHistory;
	private int m_maxTransaction, m_transactionCounter;
	
	/**
	 * Zero param constructor
	 */
	public Chequing(){
		this("", "", 0.00, 0.25, 3);
	}
	
	/**
	 *
	 * Five param constructor
	 * 
	 * @param name - The full name on the account
	 * @param accountNumber - The account number
	 * @param balance - The current balance
	 * @param fee - amount of service fees per transaction
	 * @param numOfTransactions - The maximum amount of transaction this account can make
	 * 
	 */
	public Chequing(String name, String accountNumber, double balance, double fee, int numOfTransactions){
		
		super(name, accountNumber, balance);
		
		if(fee < 0)
			fee = .25;
		m_serviceFee = new BigDecimal(fee);
		
		if(numOfTransactions < 0)
			numOfTransactions = 3;
		m_maxTransaction = numOfTransactions;
		
		m_transactionHistory = new BigDecimal[m_maxTransaction];
	
		m_transactionCounter = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(m_transactionHistory);
		result = prime * result + Objects.hash(m_maxTransaction, m_serviceFee);
		return result;
	}

	/**
	 * Makes a call to the superclass' .equals()
	 * and adds the conditions that the maximum amount of transactions,
	 * the service fee, and transaction history must also be the same
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (!super.equals(obj))
			return false;
		
		if (!(obj instanceof Chequing))
			return false;
		
		Chequing other = (Chequing) obj;
		
		return m_maxTransaction == other.m_maxTransaction
				&& m_serviceFee.equals(other.m_serviceFee)
				&& Arrays.equals(m_transactionHistory, other.m_transactionHistory);
	}
	
	@Override
	public String toString() {
		
		String fields[] =  {"Account Type",
							"Service Charge",
							"Toatal Charges",
							"List of Transactions",
							"Final Blance"};

		// Setting formatting width to the longest string		
		int width = fieldSize(fields);

		StringBuffer ret = new StringBuffer(super.toString());
			
		ret.append(String.format("%-" + width +"s",fields[0])).append(": CHQ\n")
		   
		   .append(String.format("%-" + width +"s",fields[1])).append(": $")
		   .append(String.format("%.2f", m_serviceFee))
		   
		   .append(String.format("\n%-" + width +"s",fields[2])).append(": $")
		   .append(String.format("%.2f",getTotalFees()))
		   
		   .append(String.format("\n%-" + width +"s",fields[3])).append(": ");
		if(m_transactionCounter > 0) {
			
			//This block handles adding commas or newlines depending 
			//on the amount of transactions
			for(int i = 0; i < m_transactionCounter; i++ ) {
				if(i < m_transactionCounter - 1) {
					if(m_transactionHistory[i].doubleValue() > 0) 
						ret.append('+').append(String.format("%.2f",m_transactionHistory[i])).append(", ");
					else
						ret.append(String.format("%.2f",m_transactionHistory[i])).append(", ");
				}
				else {
					if(m_transactionHistory[i].doubleValue() > 0) 							
						ret.append('+').append(String.format("%.2f",m_transactionHistory[i])).append('\n');
					else
						ret.append(String.format("%.2f",m_transactionHistory[i])).append('\n');
				}
			}
		}
		else
			ret.append("No Transactions made\n");
		
		ret.append(String.format("%-" + width +"s",fields[4])).append(": $")
		   .append(String.format("%.2f",getBalance())).append('\n');
				
		return ret.toString();
	}
	
	/**
	 * 
	 * @return - the amount of service fees per transaction
	 */
	public double getServiceFee() {
		return m_serviceFee.doubleValue();
	}
	
	/**
	 * 
	 * @return - the maximum amount of transactions allowed
	 */
	public int getMaxTransactions() {
		return m_maxTransaction;
	}
	
	/**
	 * 
	 * @return - The total amount of service fees the account has accumulated
	 */
	public double getTotalFees() {
		
		BigDecimal count = new BigDecimal(m_transactionCounter);
		
		return 	m_serviceFee.multiply(count).doubleValue();
	}
	
	/**
	 * @Override - Calculates the final balance after taking out the 
	 * transaction fees
	 */
	public double getBalance(){
			
		BigDecimal fees = new BigDecimal(getTotalFees());
		BigDecimal fin = new BigDecimal(super.getBalance());
		
		return fin.subtract(fees).doubleValue();
	}
	
	/**
	 * @Override - Also records a successful withdraw in transaction history
	 * and can now also throw TransactionLimit exception
	 */
	public boolean withdraw(double amount) throws AccountExceptions {
		
		boolean withdrawn = false;
				
		if(m_transactionCounter < m_maxTransaction) {
			
			if(super.withdraw(amount)) {
				
				withdrawn = true;
			
				m_transactionHistory[m_transactionCounter++] = new BigDecimal(-amount);	
			}
		}
		else
			throw new TransactionLimit("Have already reached maximum amount of transactions");
		
		return withdrawn;
	}
	
	/**
	 * @Override - Also records a successful deposit in transaction history
	 * and can now also throw TransactionLimit exception
	 */
	public void deposit(double amount) throws AccountExceptions {
		
		if(m_transactionCounter < m_maxTransaction) {
		
			super.deposit(amount);
			
			m_transactionHistory[m_transactionCounter++] = new BigDecimal(amount);
		}
		else 
			throw new TransactionLimit("Have already reached maximum amount of transactions");
	}
}