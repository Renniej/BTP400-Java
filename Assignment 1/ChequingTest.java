import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChequingTest {

	Chequing c;
	
	@Test
	void testChequing() {
		
		c = new Chequing();
		
		assertEquals("", c.getFullName());
		assertEquals("", c.getAccountNumber());
		assertEquals(0.00, c.getBalance());
		assertEquals(.25, c.getServiceFee());
		assertEquals(3, c.getMaxTransactions());
	}

	
	/**
	 * Testing custom constructor
	 */
	@Test
	void testChequingStringStringDoubleDoubleInt() {
		
		c = new Chequing(null, null, -500, -.45, -5);
		
		assertEquals("", c.getFullName());
		assertEquals("", c.getAccountNumber());
		assertEquals(0.00, c.getBalance());
		assertEquals(.25, c.getServiceFee());
		assertEquals(3, c.getMaxTransactions());
	}


	@Test
	void testWithdraw() {
		
		c = new Chequing(null, null, 500, .25, 3);
		
		
		//Testing if withdraw updates the balance correctly
		//And attempting to take out more then what is available
		//And that a failed withdraw returns false
		try {
			c.withdraw(50);  // True
			assertFalse(c.withdraw(500)); // Insufficient Funds 
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		
		//Testing passing a negative as a parameter
		//And that passing a negative returns false
		try {
			assertFalse(c.withdraw(-55)); // Must Be Positive
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		
		assertEquals((500-50)-c.getTotalFees(), c.getBalance());
		
	}

	@Test
	void testDeposit() {
		
		c = new Chequing();
		
		//tests if deposit updates the balance properly
		//And that passing a negative value does nothing
		try {
			c.deposit(100);
			c.deposit(-55); // Must Be Positive
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		assertEquals(100 - c.getTotalFees(), c.getBalance());
	}


	
	/**
	 * Tests that the total amount of service fees are counted correctly
	 * and also tests exceeding the transaction limit of both withdraw 
	 * and deposit
	 */
	@Test
	void testGetTotalFees() {
		
		c = new Chequing(); // service fee == .25
		
		try {
		c.deposit(100); // .25
		c.deposit(50); 	// .50
		c.withdraw(25); // .75
		c.withdraw(25); // Would be 1.00 but already at Transaction Limit
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		
		try {
			c.deposit(25); // Would be 1.00 but already at Transaction Limit
			}
			catch(AccountExceptions ex) {
				System.out.println(ex);
			}
		
		assertEquals(.75, c.getTotalFees());		
	}
}
