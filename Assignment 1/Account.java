/**
 * A1
 * 
 * @author troberts10
 * @date Feb-2020
 * @version 1.0
 * 
 */

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
	
	private String m_name, m_accountNumber;
	private BigDecimal m_balance;
	
	/**
	 * Default Account Constructor
	 */
	Account(){this("", "", 0.00);}
	
	/**
	 * Three param Account Constructor
	 * @param name - The full name on the account
	 * @param accountNumber - The account number
	 * @param balance - The current balance
	 */
	Account(String name, String accountNumber, double balance){
		
		if(name == null)
			name = "";	
		m_name = name;
		
		if(accountNumber == null)
			accountNumber = "";
		m_accountNumber = accountNumber;
		
		if(balance < 0)
			balance = 0.00;
		m_balance =  new BigDecimal(balance);
	}

	/**
	 * @return - returns the full name of the account holder
	 */
	String getFullName(){
		return m_name;
	}
	
	/**
	 * @return - - returns the first name of the account holder
	 */
	String getFirstName(){
		
		String first;
		
		if(m_name != "") {
			
			String[] token = m_name.split(" ");
			
			first = token[0];
		}
		else
			first = "";
		
		return first;
	}
	/**
	 * @return - returns the last name of the account holder
	 */
	String getLastName(){
		
		String last;
		
		if(m_name != "") {
			
			String[] token = m_name.split(" ");
				
			last = token[1];
		}
		else
			last = "";
			
		return last;
	}
	
	/**
	 * @return - returns the current account number 
	 */
	String getAccountNumber(){
		return m_accountNumber;
	}
	
	/**
	 * @return - returns the current balance
	 */
	 double getBalance(){
		return m_balance.doubleValue();
	}

	
	@Override 
	public String toString(){
		
		String one, two, three;
		int width;
		
		one = "Name";
		two = "Number";
		three = "Current Balace";
		
	// Setting formatting width to the longest string		
		width = three.length();
		
	// Formatting the field width	
		one = String.format("%-" + width +"s", one);
		two = String.format("%-" + width +"s", two);
		
		StringBuffer ret = new StringBuffer(one);
		
		ret.append(": ").append(getLastName());
		
		if(m_name != "") 
			ret.append(", ").append(getFirstName());
		ret.append('\n')
		
		   .append(two).append(": ").append(getAccountNumber()).append('\n')
		   .append(three).append(": $").append(String.format("%.2f",m_balance)).append('\n');
		
		return  ret.toString(); 
	}

	@Override
	public int hashCode() {
		return Objects.hash(m_accountNumber, m_balance, m_name);
	}

	/**
	 * Accounts are equal if
	 * m_name && m_accountNumber && m_balance
	 * are the same in both objects
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof Account))
			return false;
		
		Account other = (Account) obj;
		
		return Objects.equals(m_accountNumber, other.m_accountNumber) && 
			   m_balance.equals(other.m_balance) && 
			   Objects.equals(m_name, other.m_name);
	}
	
	/**
	 * Attempts to take money away from current balance.
	 * Amount must be positive
	 * Amount must be less than current balance
	 * @param amount
	 * @return true if successfully withdrawn
	 * @throws AccountExceptions
	 */
	boolean withdraw(double amount) throws AccountExceptions {
		
		boolean withdrawn = false;
		
		if(amount > 0) {
			
			BigDecimal am = new BigDecimal(amount);
			
			if(am.compareTo(m_balance) < 0) {
				m_balance = m_balance.subtract(am);
				withdrawn = true;
			}
			else
				throw new InsufficientFunds("Not enough money in account");
		}
		else
			throw new MustBePositive("Cannot withdraw a negative amount");
		
		return withdrawn;
	}
	
	/**
	 * Attempts to add the parameter to the current balance.
	 * Amount must be greater than 0
	 * @param amount
	 * @throws AccountExceptions
	 */
	void deposit(double amount) throws AccountExceptions {
		
		if(amount > 0) {
			BigDecimal add = new BigDecimal(amount);
			
			m_balance = m_balance.add(add);
		}
		else
			throw new MustBePositive("Cannot deposit a negative amount");
	}
}


/**
 * Account exceptions Superclass
 * @author TIM
 */
@SuppressWarnings("serial")
class AccountExceptions extends Exception{
	AccountExceptions(String s){
		super(s);
	}
}

/**
 *Insufficient funds exception 
 * @author TIM
 */
@SuppressWarnings("serial")
class InsufficientFunds extends AccountExceptions{
	InsufficientFunds(String s){
		super(s);
	}
}

/**
 *Must be a positive number exception 
 * @author TIM
 */
@SuppressWarnings("serial")
class MustBePositive extends AccountExceptions{
	MustBePositive(String s){
		super(s);
	}
}