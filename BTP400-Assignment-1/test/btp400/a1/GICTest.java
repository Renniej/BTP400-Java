package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.seneca.accounts.GIC;
import com.seneca.exceptions.NotAvailableForGIC;

/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Testing the GIC class
 * 
 */
class GICTest {
	
	GIC g;


	/**
	 * Testing the zero param constructor
	 */
	@Test
	void testGIC() {
		
		g = new GIC();
		
		assertEquals("", g.getFullName());
		assertEquals("", g.getAccountNumber());
		assertEquals(0.00, g.getBalance());
		assertEquals(1, g.getInvestmentPeriod());
		assertEquals(1.25, g.getIntrestRate());
	}

	
	/**
	 * Testing the five param constructor
	 */
	@Test
	void testGICStringStringDoubleIntDouble() {
		
		g = new GIC(null, null, -900, -1, -2.25);
		
		assertEquals("", g.getFullName());
		assertEquals("", g.getAccountNumber());
		assertEquals(0.00, g.getBalance());
		assertEquals(1, g.getInvestmentPeriod());
		assertEquals(1.25, g.getIntrestRate());
	}
	
	
	/**
	 * Testing that getBalance is overriden properly
	 * and that it returns the correct amount
	 */
	@Test
	void testGetBalance() {
	
		g = new GIC(null, null, 1000, 2, 1.25);
	
		assertEquals(1025.15625, g.getBalance());
	}

	/**
	 * Testing that withdraw returns false
	 */
	@Test
	void testWithdraw() {
		
		g = new GIC(null, null, 500, 1, 1.25);
		
		assertFalse(g.withdraw(50)); // Not allowed in GIC 
	}

	
	/**
	 * Testing that deposit does nothing for GIC
	 */
	@Test
	void testDeposit() {
		
		g = new GIC(null, null, 1000, 2, 1.25);
		
		try {
		g.deposit(1000); // Not allowed in GIC
						// If deposit was successful the balance 
						//expected would be 2050.31
		}
		catch(NotAvailableForGIC ex) {
			System.out.println(ex);
		}
		
		assertEquals(1025.15625, g.getBalance());
	}


	/**
	 * Testing that the proper amount of interest is calculated
	 */
	@Test
	void testGetInterestIncome() {
		
		g = new GIC(null, null, 1000, 2, 1.25);
		
		assertEquals(25.15625, g.getInterestIncome());
	}
	
	/**
	 * Testing that the proper amount of interest is calculated
	 * with a different number
	 */	
	@Test
	void testGetInterestIncome2() {
		
		g = new GIC(null, null, 2000, 2, 1.25);
		
		assertEquals(50.3125, g.getInterestIncome());
	}
}
