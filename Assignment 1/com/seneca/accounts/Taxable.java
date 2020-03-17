package com.seneca.accounts;

import java.math.BigDecimal;

public interface Taxable {
	
	final BigDecimal TAX = new BigDecimal(.15);

	void calculateTax();
	double getTaxAmount();
	String creatTaxStatement();
}
