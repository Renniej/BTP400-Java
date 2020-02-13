import java.math.BigDecimal;
import java.util.Objects;

public class GIC extends Account implements Taxable {
	
	
	//the period of investment (in years) 
	private int m_investment_period;
	//get how much u should be taxed???
	private double m_tax_amount;
	//annual interest rate (in percentage).
	private BigDecimal m_interest_rate;
	
	



	// initializes the period of investment to 1 year and annual interest rate to 1.25%
	public GIC() {
		super();
		this.m_investment_period = 1;
		this.m_interest_rate = new BigDecimal("1.25");
		this.m_tax_amount = 0;
	}

	
	public GIC(String name, String accountNumber, double starting_balance, int investment_period, double interest_rate) {
		super(name, accountNumber, starting_balance);
		
		this.m_investment_period = investment_period;
		this.m_interest_rate = new BigDecimal(interest_rate);
		this.m_tax_amount = 0;
	}
	
	public double getInterestIncome() {
		BigDecimal starting_balance = new BigDecimal(super.getBalance());

		BigDecimal income_maturity = new BigDecimal(this.getBalance());
		
		return starting_balance.subtract(income_maturity).doubleValue();
		
	}

	@Override
	public void calculateTax() {
		
		BigDecimal taxable_income = new BigDecimal(this.getInterestIncome());
		
		this.m_tax_amount = taxable_income.multiply(tax).doubleValue();;
		
		

	}




	@Override
	public double getTaxAmount() {
		
		return m_tax_amount;
	}

	@Override
	public String creatTaxStatement() {
		
		
		
		StringBuffer output = new StringBuffer("Tax rate : ");
		output.append(String.format("%.2f", tax.doubleValue() * 100));
		output.append("%");
		
		output.append("\nAccount Number : ");
		output.append(this.getAccountNumber());
		
		
		output.append("\nInterest income : $");
		output.append(String.format("%.2f",this.getInterestIncome()));
		
		output.append("\nAmount of tax : $");
		output.append(String.format("%.2f",this.getTaxAmount()));
		
		
		
		return output.toString();
	}
	
	
	@Override
	/*Balance at Maturity = Current/Starting Balance x ( 1 + r ) ^ t
	r = annual interest rate
	t = number of years (i.e. period of investment) */
	double getBalance() {
		
		BigDecimal start_bal = new BigDecimal(super.getBalance());
		
		 
		return start_bal.multiply(m_interest_rate.divide(new BigDecimal(100)).add(new BigDecimal(1)).pow(m_investment_period)).doubleValue();
		
		
	}


	@Override
	boolean withdraw(double amount) {
		return false;
	}


	@Override
	void deposit(double amount)  {
		
	}
	
	
	
	
	
	@Override
	public String toString() {
		
		
		
		StringBuffer output = new StringBuffer(super.toString());
		output.append("Account Type : GIC");


		output.append("\nAnnual Interest Rate :");
		output.append(String.format("%.2f",m_interest_rate.doubleValue()));
		output.append("%");
		
		output.append("\nPeriod of Investment :  ");
		output.append(m_investment_period);
		
		output.append("\nInterest Income at Maturity: $");
		output.append(this.getInterestIncome());
		
		output.append("\nBalance at Maturity : $");
		output.append(this.getBalance());
		
		output.append("\n");
		
		
		return output.toString();
	}
	
	
	


	@Override
	//Note to self : this equals also calls the accounts hashcode
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(m_interest_rate, m_investment_period) + super.hashCode();
		return result;
	}


	@Override
	//Note to self : this equals also calls Account's equals
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GIC))
			return false;
		GIC other = (GIC) obj;
		return Objects.equals(m_interest_rate, other.m_interest_rate)
				&& Double.doubleToLongBits(m_investment_period) == Double.doubleToLongBits(other.m_investment_period) && super.equals(other);
	}

}
