import java.text.DecimalFormat;
import java.text.NumberFormat;



public class ArrayTester {

	
	
	public static boolean inIgnoreIndex(int index, int[] indexArray) {
		
		
		boolean inArray = false;
		
		for (int i = 0; i < indexArray.length; ++i) {
			if (indexArray[i] == index) {
				inArray = true;
				break;
			}
		}
		
		return inArray;
		
	}
	
	
	public static void main(String[] args) {
		
		Account accounts[];
		accounts = new Account[7];
		
		accounts[0] = new Account("Peter Liu", "A12345", 5000);
		accounts[1] = new Account("Peter Liu", "A67890", 6000);
		accounts[2] = new Account("Abraham Lincoln", "Z6789",7777);
		accounts[3] = new Account("Peter Liu", "A12345", 5000);
		accounts[4] = new Account("Abraham Lincoln", "Z6789",7777);
		accounts[5] = new Account("Abraham Lincoln", "Z6789",7777);
		accounts[6] = new Account("Tai-Juan Rennie", "E3333", 9000);
			
				
		int[] ignoreIndex = new int[accounts.length];
		int ignoreIndexCount = 0;
		int numOfEntries= 0;
		
		//set all values to ignoreIndex to -1
		for (int i = 0; i < ignoreIndex.length; ++i)
			ignoreIndex[i] = -1;
		
		
		
		//Print out data
		System.out.println("\nCOUNTING SUMMARY\n+ total number of accounts: " + accounts.length);
		//For each element in the array compare it to every element in the array
		
		for (int i = 0; i < accounts.length; ++i) { 
			
			int numOfDups = 0;
			
			if (inIgnoreIndex(i, ignoreIndex)) { //if this index has already been counted as a duplicate then ignore it
				continue;
			}
			else {
				
				for (int j = 0; j < accounts.length; ++j) {
					
					if (inIgnoreIndex(j, ignoreIndex) || i == j ) { //if this index has already been counted as a duplicate then ignore it OR is being compared to itself
						continue;
					}
					else {
						
						if (accounts[i].equals(accounts[j])) {
							
							ignoreIndex[ignoreIndexCount] = j;
							ignoreIndexCount++;
							numOfDups++;
							
						}
					}
					
					
					
					
					
				}
				
				System.out.println(++numOfEntries + ". " + accounts[i].getFullName() + ", " + accounts[i].getAccountNumber() + ", " +  String.format("%.2f",accounts[i].getAccountBalance()) + ": " + (numOfDups + 1));//+1 to account for the current object
				ignoreIndex[ignoreIndexCount] = i;
				ignoreIndexCount++;
				//System.out.println(Arrays.toString(ignoreIndex));
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
	}

}
