import java.math.BigDecimal;

public interface Taxable {
	
	final BigDecimal tax = new BigDecimal(.15);
	
	//final double tax = .15;
	
	void calculateTax();
	double getTaxAmount();
	String creatTaxStatement();
}
