package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.seneca.accounts.GIC;
import com.seneca.exceptions.NotAvailableForGIC;

class GICTest {
	
	GIC g;

	@Test
	void testGIC() {
		
		g = new GIC();
		
		assertEquals("", g.getFullName());
		assertEquals("", g.getAccountNumber());
		assertEquals(0.00, g.getBalance());
		assertEquals(1, g.getInvestmentPeriod());
		assertEquals(1.25, g.getIntrestRate());
	}

	@Test
	void testGICStringStringDoubleIntDouble() {
		
		g = new GIC(null, null, -900, -1, -2.25);
		
		assertEquals("", g.getFullName());
		assertEquals("", g.getAccountNumber());
		
		//*** TODO ***
		 //Need to add a condition for if (super.getBalance() <= 0) ? Don't apply intrest
		assertEquals(0.00, g.getBalance());
		
		//*** TODO ***
		//Need to handle negative values being passed for both of these
		assertEquals(1, g.getInvestmentPeriod());
		assertEquals(1.25, g.getIntrestRate());
	}
	
	@Test
	void testGetBalance() {
	
		g = new GIC(null, null, 1000, 2, 1.25);
	
		assertEquals(1025.15625, g.getBalance());
	}

	@Test
	void testWithdraw() {
		
		g = new GIC(null, null, 500, 1, 1.25);
		
		assertFalse(g.withdraw(50)); // Not allowed in GIC 
	}

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


	@Test
	void testGetInterestIncome() {
		
		g = new GIC(null, null, 1000, 2, 1.25);
		
		//*** TODO ***
		//Figure out how/why it's storing a negative value
		//It's the correct value, but should be positive
		System.out.println(g.getInterestIncome());
		
		assertEquals(25.15625, g.getInterestIncome());
	}
	
	@Test
	void testGetInterestIncome2() {
		
		g = new GIC(null, null, 2000, 2, 1.25);
		
		//*** TODO ***
		//Figure out how/why it's storing a negative value
		//It's the correct value, but should be positive
		System.out.println(g.getInterestIncome());
		
		assertEquals(50.3125, g.getInterestIncome());
	}

}
