package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.seneca.accounts.Account;

class AccountTest {

	Account a;
	
	@Test
	void testAccount() {
		a = new Account();
		
		assertEquals("", a.getFullName());
		assertEquals("", a.getAccountNumber());
		assertEquals(0.00, a.getBalance());
	}

	@Test
	void testAccountStringStringDouble() {
		a = new Account(null, null, -555);
		
		assertEquals("", a.getFullName());
		assertEquals("", a.getAccountNumber());
		assertEquals(0.00, a.getBalance());
	}

}
