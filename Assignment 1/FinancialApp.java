import java.util.InputMismatchException;
import java.util.Scanner;

public class FinancialApp {
	
	
	private static Bank m_Bank;
	
	

	public static void main(String args[]) {
	
		boolean userDone = false;
		
		
		do {
			
			DisplayMenu();
			
			
			
		} while(userDone == false);
		
		
		
		
	}
	
	
	public static void loadBank(Bank bank) {
		if (bank != null) {
			m_Bank = bank;
		}
	}
	
	public static void DisplayMenu() {
		
		StringBuffer menu = new StringBuffer("\nWelcome to Seneca@York Bank!\n");
		menu.append("1. Open an account.\n");
		menu.append("2. Close an account.\n");
		menu.append("3. Deposit money.\n");
		menu.append("4. Withdraw money.\n");
		menu.append("5. Display accounts.\n");
		menu.append("6. Display a tax statement.\n");
		menu.append("7. Exit.");
		
	
	
		String msg_accountInfo = "Please enter account information at one line";
		
		System.out.println(menu);
		
		
	}
	
	public static int menuChoice() {
		
		Scanner userInput = new Scanner(System.in);
		int int_userInput = -1;
		boolean valid_input = true;
		
		
		do {
			
			System.out.println("\nPlease enter your choice : ");
			
			try {
				
				int_userInput = userInput.nextInt();
				
				if (!(int_userInput >= 1 && int_userInput <=7)) {
					valid_input = false;
					System.out.println("\nThat is not a valid option. Try Again.");
				}
				
			}catch(InputMismatchException e) {
				
				valid_input = false;
				System.out.println("\nThat was not a valid input. Try Again.");
				
				
			}catch(Exception e) {
				valid_input = false;
				System.out.println("\nOops.. Something unexpected happended. Try Again.");
			}
			
		} while(valid_input == false);
		
		
		return int_userInput;
		
		
		
	}
	
	
	public static void displayAccount(Account acct) {
		System.out.println(acct);
	}
	public static void option5_SubMenu() {
		
		
		
		
		
	}
	
}
