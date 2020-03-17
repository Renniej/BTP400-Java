import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
