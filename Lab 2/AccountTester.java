/**
* The Account Tester program implements an application that
* simply displays the data held inside an account object to the standard output.
*
* @author  Tai-Juan Rennie
* @version 1.0
* @since   2020-01-12 
*/

public class AccountTester {

	public static void main(String[] args) {
	
		
		Account test =  new Account();
		Account test2 = new Account("Tai-Juan", "00001", 42);
		Account test3 = new Account(null, null, -1);
		Account test4 = null;
		Account test5 = null;
		
		
		try {
			test4 = (Account) test2.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone creation failure ):\n");
		}

		System.out.println("---Testing Getters/Setters---");
		
		System.out.println(test); 
		System.out.println(test2);
		System.out.println(test3);
		
		test3.setFullName("A DIFFERNT NAME!");
		test3.setAccountBalance(-3000.21);
		test3.setAccountNumber("00002");
		
		System.out.println(test3);
		
		System.out.println("---Testing equals()---");
		
		System.out.println("Test4 equals Test2 = true : " +  (test4.equals(test2) ? "PASSED" : "FAILED"));
		System.out.println("Test equals Test2 = false : "  + (test.equals(test2) ? "FAILED" : "PASSED") );
		System.out.println("Test equals test5(null reference) = false : "  + (test.equals(test5) ? "FAILED" : "PASSED") );
		
	}
	
}
