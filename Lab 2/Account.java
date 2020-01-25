import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
* The account class is a java class that holds an account holder's full name, account number and current balance
*
* @author  Tai-Juan Rennie
* @since   2020-01-12 
*/

public class Account implements Cloneable{

	
	
	/**Account holder's full name*/
	private String m_Full_Name; 
	
	/**Account Number*/
	private String m_Acct_Num; 
	
	/** Account's Current Balance*/
	private BigDecimal  m_Cur_Balance; 
	
	
	

	/**Default constructor for the Account class*/
	public Account() { 
		this(null,null,'0');
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
			this.m_Cur_Balance = BigDecimal.valueOf(0);
		else
			this.m_Cur_Balance = new BigDecimal(p_Cur_Balance);
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
		return this.m_Cur_Balance.doubleValue();
	}
	
	//toString()
	public String toString() {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US); //Used for formatting decimal number to 2 decimals (Money = $0.00)
		
		return 	"Account Name   : " + getFullName() + "\n" +
				"Account Number : " + getAccountNumber() + "\n" +
				"Balance        : " + formatter.format(getAccountBalance()) + "\n";
		
	}
	

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_Acct_Num == null) ? 0 : m_Acct_Num.hashCode());
		result = prime * result + ((m_Cur_Balance == null) ? 0 : m_Cur_Balance.hashCode());
		result = prime * result + ((m_Full_Name == null) ? 0 : m_Full_Name.hashCode());
		return result;
	}

	
	/**2 account objects are considered equals when both have the same Name, Account ID and Balance variable */
	public boolean equals(Object anObj) {
		
		if (anObj instanceof Account) {
			Account acc = (Account)anObj;
			return this.getFullName().equals(acc.getFullName()) && this.getAccountNumber().equals(acc.getAccountNumber()) && (this.getAccountBalance() == acc.getAccountBalance());
		}
		else {
			return false;
		}
	}

	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	
	
	
	
}
