import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Bank {

	
	private String m_Bank_Name;
	private ArrayList<Account> m_Accounts;
	
	
	
	public Bank() {
		m_Bank_Name = "Seneca@York";
		m_Accounts = new ArrayList<Account>();
	}
	
	public Bank(String name) {
		this();
		
		if (name != null) {
			m_Bank_Name = name;
		}
		
	}
	
	public String getBank_Name() {
		return m_Bank_Name;
	}
	
	public void setBank_Name(String m_Bank_Name) {
		this.m_Bank_Name = m_Bank_Name;
	}
	
	public ArrayList<Account> getAccounts() {
		return m_Accounts;
	}
	



	public boolean addAccount(Account newAccount) {
		
		boolean accAdded = false; //assume an account wont be added until proven otherwise
		
		if (newAccount != null && !findAccount(newAccount) ) { //if no existing account matches newAccount then add it
			m_Accounts.add(newAccount);
			accAdded = true;  //when successfully add trigger accAdded flag
		}
		
		
		return accAdded; //return accAdded value
		
		
		
		
	}
	
	
	public Account[] searchByBalance(double bal) {
		
		ArrayList<Account> matchedAccounts = new ArrayList<Account>();
		Account[] accs;
		
		
		for(Account acc : m_Accounts) {
		
			if (acc.getAccountBalance() == bal) {
				matchedAccounts.add(acc);
			}
		}
		
		accs = matchedAccounts.toArray(new Account[matchedAccounts.size()]);
		return  accs;
		
		
	}
	
	public boolean findAccount(Account queryAcc) {
		
		boolean accFound = false; //Assume no account matches query account
		
		
		if (queryAcc != null) {
			
			for (Account acc : m_Accounts) { //search m_Accounts arrayList to find matching account
				if (queryAcc.getAccountNumber().equals(acc.getAccountNumber())) {
					accFound = true;  //if found then set accFound flag to true and break for loop;
					break;
				}
			}
			
		}
		
		return accFound; //return accFound flag value;
		
	}
	


	
	
	public String toString() {
		
		int counter = 0;
		String str =  "*** Welcome to the Bank of " + getBank_Name() +  "***\n"
				+ "It has " + m_Accounts.size() + " accounts.\n"; 
		
		
		for (Account acc : this.m_Accounts) {
			
			str += ++counter + ". number: " + acc.getAccountNumber() + " name: " + acc.getFullName() + " balance: $" + String.format("%.2f",acc.getAccountBalance()) + "\n";
		}
		
		str+= "\n";
		
		return str;
		
		
		
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
		
	
		
	    
		return Objects.equals(m_Accounts, other.m_Accounts) && Objects.equals(m_Bank_Name, other.m_Bank_Name);
	}

}