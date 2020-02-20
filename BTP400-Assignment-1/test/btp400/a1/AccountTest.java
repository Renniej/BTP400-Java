
package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.seneca.accounts.Account;

/**
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Testing Account Class
 * 
 */
class AccountTest {

	Account a;
	
	/**
	 * Testing the zero param constructor
	 */
	@Test
	void testAccount() {
		
		a = new Account();
		
		assertEquals("", a.getFullName());
		assertEquals("", a.getAccountNumber());
		assertEquals(0.00, a.getBalance());
	}

	
	/**
	 * Testing the Account 3 param constructor
	 */
	@Test
	void testAccountStringStringDouble() {
		
		a = new Account(null, null, -555);
		
		assertEquals("", a.getFullName());
		assertEquals("", a.getAccountNumber());
		assertEquals(0.00, a.getBalance());
	}
	
	
	/**
	 * Testing that get last name properly handles extra white spaces
	 */
	@Test
	void testgetLastName() {

		a = new Account("Tim D.D.     Roberts", "A765", 5000);
		
		assertEquals("Roberts", a.getLastName());
	}

}
