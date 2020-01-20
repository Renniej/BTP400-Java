import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
* The account class is a java class that holds an account holder's full name, account number and current balance
*
* @author  Tai-Juan Rennie
* @since   2020-01-12 
*/

public class Account {

	
	
	/**Account holder's full name*/
	String m_Full_Name; 
	
	/**Account Number*/
	String m_Acct_Num; 
	
	/** Account's Current Balance*/
	double m_Cur_Balance; 
	
	
	

	/**Default constructor for the Account class*/
	public Account() { 
		setFullName(null);
		setAccountNumber(null);
		setAccountBalance(0);
	}
	
	
	/**
	* 3 arg Constructor for the Account class 
	* @param p_Name used for setting the name of the Account
	* @param p_Acct_Num used to set Account's account number.
	* @param p_Cur_Balance used to set the Account's current balance
	*/
	

	public Account(String p_Name, String p_Acct_Num, double p_Cur_Balance) {
		
	
		setFullName(p_Name);
		setAccountNumber(p_Acct_Num);
		setAccountBalance(p_Cur_Balance);
		
	
	}
	
	
	//Setters
	public void setFullName(String p_Name) {
		
		if (p_Name == null) 
			this.m_Full_Name = "";
		
		else
			this.m_Full_Name = p_Name;
		
	}
	
	
	public void setAccountNumber(String p_Acct_Num) {
		if (p_Acct_Num == null)
			this.m_Acct_Num = "";
		else
			this.m_Acct_Num = p_Acct_Num;
	}
	
	public void setAccountBalance(double p_Cur_Balance) {
		if (p_Cur_Balance < 0)
			this.m_Cur_Balance = 0;
		else
			this.m_Cur_Balance = p_Cur_Balance;
	}
	
	//Getters
	
	/**
	 * 
	 * @return Account's m_Full_Name 
	 */
	public String getFullName() {
		return this.m_Full_Name;
	}
	
	/**
	 * 
	 * @return Account's m_Acct_Num 
	 */
	public String getAccountNumber() {
		return this.m_Acct_Num;
	}
	
	/**
	 * 
	 * @return Account's Balance 
	 */
	public double getAccountBalance() {
		return this.m_Cur_Balance;
	}
	
	//toString()
	public String toString() {
		
		NumberFormat formatter = new DecimalFormat("#0.00"); //Used for formatting decimal number to 2 decimals (Money = $0.00)
		
		return 	"Account Name   : " + getFullName() + "\n" +
				"Account Number : " + getAccountNumber() + "\n" +
				"Balance        : $" + formatter.format(getAccountBalance()) + "\n";
		
	}
	
	
	
	
	
}
