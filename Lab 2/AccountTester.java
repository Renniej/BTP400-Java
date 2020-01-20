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
	
		
		Account test = new Account();
		Account test2 = new Account("Tai-Juan", "00001", 42);
		Account test3 = new Account(null, null, -1);
		
	
		
		System.out.println(test); 
		System.out.println(test2);
		System.out.println(test3);
		
		test3.setFullName("A DIFFERNT NAME!");
		test3.setAccountBalance(-3000.21);
		test3.setAccountNumber("00002");
		
		System.out.println(test3);
	}
	
}
