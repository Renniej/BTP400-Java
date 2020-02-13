/**
 * A1
 * 
 * @author troberts10
 * @date FEB-2020
 * @version 1.0
 * 
 * 
 * This is the main class that tests the functionality of the Account class
 */
public class AccountTester {
	
	public static void main(String[] args) {
		
		Account a = new Account();
		Account b = new Account(null, null, -1);
		Account c = new Account("Tim Roberts", "104531181", 587_898.33);

		System.out.println("********* Testing Account equals() *********\n");
		
		System.out.println("Account A: \n" + a);
		System.out.println("Account B: \n" + b);
		System.out.println("Account C: \n" + c);
		
		try {
			a.deposit(6000.55);
			System.out.println("Account A: \n" + a);
		}
		catch(AccountExceptions ex) {
			System.out.println("--------------------------------");
			System.out.println(ex);
			System.out.println("--------------------------------\n");
		}
		
		try {
			a.withdraw(7000);
			System.out.println("Account A: \n" + a);
		}
		catch(AccountExceptions ex) {
			System.out.println("---------------------------------");
			System.out.println(ex);
			System.out.println("---------------------------------\n");
		}
		
		try {
			a.withdraw(445);
			System.out.println("Account A: \n" + a);
		}
		catch(AccountExceptions ex) {
			System.out.println("------------------------------");
			System.out.println(ex);
			System.out.println("------------------------------\n");				
		}
	}
}
