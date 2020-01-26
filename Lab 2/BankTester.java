import java.util.ArrayList;
import java.util.Arrays;

public class BankTester {

	public static void main(String[] args) {
	
		
		
		
		String accountNames[] = {"John Doe", "Mary Ryan", "Peter Liu", 
                "John Doe", "Peter Liu" };
		
		String accountNumbers[] = { "A1234", "B5678", "C9999", "A1234", "D8901" };
		int accountBalances[]   = { 1000, 3000, 5000, 7000, 3000 };
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		ArrayList<Account> accounts2  = new ArrayList<Account>();
		
		Bank bank1 = new Bank();
		Bank bank2 = new Bank("Tai-Juan Rennie");
		Bank bank3 = new Bank("TEST_Equals");
		Bank bank4 = new Bank("Tai-Juan Rennie");
		Bank bank5 = new Bank("Tai-Juan Rennie");
		String str;
		
		for (int i = 0; i < 5; ++i) { //acccounts in normal order
			
			accounts.add(new Account(accountNames[i], accountNumbers[i], accountBalances[i]));
			
		}
		
		
		for (int i = 4; i >= 0; --i) { //accounts added in reverse order
			
			accounts2.add(new Account(accountNames[i], accountNumbers[i], accountBalances[i]));
			
		}
		
		
		
		
		for (Account acc : accounts) {
			bank2.addAccount(acc);
			bank4.addAccount(acc);
			
		}
		
		
		for (Account acc : accounts2) {
			bank3.addAccount(acc);
			bank5.addAccount(acc);
		}
	
		
		Account[] bal_acc = bank2.searchByBalance(3000);
		Account[] bal_acc2 = bank2.searchByBalance(-1111);
		
		
		System.out.println(bank1);
		System.out.println(bank2);
		
		
		System.out.println("Testing bank2.addAccount(null) : " + (bank2.addAccount(null) ? "Account added sucessfully" : "Account added unsuccessfully"));
		
		System.out.println("\nTesting searchBalance(3000)");
		
		
		if (bal_acc.length > 0) {
			
			str = "\nWe have found " + bal_acc.length + " accounts whose balance is $" + 3000 + ".\n";
			
			for(int i = 0; i < bal_acc.length; ++i) {
				
				str+= i + ". number: " + bal_acc[i].getAccountNumber() + " name: " + bal_acc[i].getFullName() + "\n";
			}
		
			System.out.println(str);
		}
		else {
			System.out.println("\n*** NO ACCOUNT FOUND ***\n") ;
					
		}
		
		
		
		System.out.println("\nTesting searchBalance(-1111)");
		
		if (bal_acc2.length > 0) {
			
			str = "\nWe have found " + bal_acc.length + " accounts whose balance is $" + 3000 + ".\n";
			
			for(int i = 0; i < bal_acc2.length; ++i) {
				
				str+= i + ". number: " + bal_acc2[i].getAccountNumber() + " name: " + bal_acc2[i].getFullName() + "\n";
			}
		
			System.out.println(str);
			
		}
		else {
			System.out.println("\n*** NO ACCOUNT FOUND ***\n") ;
					
		}
		
		
		System.out.println("Testing bank Equals() on : bank2(Tai-Juan Rennie) & bank3(Test_Bank)");
		System.out.println("bank2.equals(bank3) = " + (bank2.equals(bank3) ? "True" : "False"));
		System.out.println("Testing bank Equals() on : bank2(Tai-Juan Rennie) & bank4(Tai-Juan Rennie)");
		System.out.println("bank2.equals(bank4) = " + (bank2.equals(bank4) ? "True" : "False"));
		System.out.println("Testing bank Equals() on : bank2(Tai-Juan Rennie) & bank5(Tai-Juan Rennie)");
		System.out.println("bank2.equals(bank5) = " + (bank2.equals(bank5) ? "True" : "False"));
	}
	
	
}
		
		
		
		

	

