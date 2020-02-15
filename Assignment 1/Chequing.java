import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class Chequing extends Account {

	private BigDecimal m_serviceFee;
	private BigDecimal[] m_transactionHistory;
	private int m_maxTransaction, m_transactionCounter;
	
	Chequing(){
		this("", "", 0.00, 0.25, 3);
	}
	
	Chequing(String name, String accountNumber, double balance, double fee, int numOfTransactions){
		
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
	
	public double getTotalFees() {
		
		double totalFees = 0.00;
	
		if(m_transactionCounter > 0) {
			totalFees = m_serviceFee.doubleValue() * m_transactionCounter;
		}
		
		return totalFees;
	}
	
	@Override
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
	
	public void deposit(double amount) throws AccountExceptions {
		
		if(m_transactionCounter < m_maxTransaction) {
		
			super.deposit(amount);
			
			m_transactionHistory[m_transactionCounter++] = new BigDecimal(amount);
		}
		else 
			throw new TransactionLimit("Have already reached maximum amount of transactions");
	}
	
	
	public double getBalance(){
			
		BigDecimal fees = new BigDecimal(getTotalFees());
		BigDecimal fin = new BigDecimal(super.getBalance());
		
		return fin.subtract(fees).doubleValue();
	}
	
	public double getServiceFee() {
		return m_serviceFee.doubleValue();
	}
	
	public int getMaxTransactions() {
		return m_maxTransaction;
	}
}

@SuppressWarnings("serial")
class TransactionLimit extends AccountExceptions{
	TransactionLimit(String s){
		super(s);
	}
}
