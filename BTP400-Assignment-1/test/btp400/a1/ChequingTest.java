
package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.seneca.accounts.*;
import com.seneca.exceptions.*;

/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020  
 */
class ChequingTest {

	Chequing c;

	/**
	 * Testing the zero param constructor
	 */
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
	 * Testing the five param constructor
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


	/**
	 * Testing the withdraw method
	 */
	@Test()
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

	
	/**
	 * Testing the deposit method
	 */
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
	
	
	/**
	 * Testing the equals override to ensure 
	 * that the transaction history fields must be the 
	 * same for a Chequing object to be equal().
	 */
	@Test
	void testEquals(){
		
		c = new Chequing(); // service fee == .25
		
		try {
		c.deposit(100); // .25
		c.deposit(50); 	// .50
		c.withdraw(25); // .75
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		
		Chequing e = new Chequing();
	
		try {
			e.deposit(100); // .25
			e.deposit(50); 	// .50
			e.withdraw(25); // .75
		}
		catch(AccountExceptions ex) {
			System.out.println(ex);
		}
		
		assertEquals(c, e);
	}
}
