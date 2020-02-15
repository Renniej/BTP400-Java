import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankTest {
	
	Bank b;
	
	@Test
	void testBank() {
		b = new Bank();
		
		assertEquals("Seneca@York", b.getBank_Name());
	}

	@Test
	void testBankString() {
		
		b = new Bank(null);
		
		assertEquals("Seneca@York", b.getBank_Name());
	}

	@Test
	void testSearchByBalance() {

		b = new Bank(null);
		
		String accountNames[]   = { "John Doe", "Mary Ryan", "Peter Liu","John Doe", "Peter Liu" };  
		String accountNumbers[] = { "A1234", "B5678", "C9999", "A1234", "D8901" };  
		int accountBalances[]   = { 1000, 3000, 5000, 7000, 3000 }; 
	
		//Initializing accounts from data arrays
		for(int i = 0; i < accountNames.length; i++)
			b.addAccount(new Account(accountNames[i], accountNumbers[i], accountBalances[i]));
		
		Account[] actual = b.searchByBalance(3000);
		
		Account[] expected = {new Account("Mary Ryan", "B5678", 3000 ),
							  new Account("Peter Liu", "D8901", 3000 )};
		
		assertArrayEquals(expected, actual);
	}
		
	@Test
	void testSearchByAccountName() {
		
		b = new Bank(null);
		
		String accountNames[]   = { "John Doe", "Mary Ryan", "Peter Liu","John Doe", "Peter Liu" };  
		String accountNumbers[] = { "A1234", "B5678", "C9999", "A1234", "D8901" };  
		int accountBalances[]   = { 1000, 3000, 5000, 7000, 3000 }; 
	
		//Initializing accounts from data arrays
		for(int i = 0; i < accountNames.length; i++)
			b.addAccount(new Account(accountNames[i], accountNumbers[i], accountBalances[i]));
		
		Account[] actual = b.searchByAccountName("Peter Liu");
		
		Account[] expected = {new Account("Peter Liu", "C9999", 5000 ),
				  			  new Account("Peter Liu", "D8901", 3000 )};

		assertArrayEquals(expected, actual);
	}

}
