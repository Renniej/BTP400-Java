import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.seneca.accounts.*;
public class client {

	
	private static int requestCode = 0;
	private static boolean bandAidFix = false;
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Waiting for server connection...");
		
		Account acc = new Account("Tai-Juan Rennie","A1234",1000);
		Account rec_acc = null;
		String str_userInput = "";
		Scanner userInput = new Scanner(System.in);
		
		Socket socket;
		
		
		 ObjectOutputStream oos;
		 ObjectInputStream is; 
		 
		 socket = new Socket(InetAddress.getByName("localhost"), 8000);
		 oos = new ObjectOutputStream(socket.getOutputStream());
		 is = new ObjectInputStream(socket.getInputStream());
			
		 System.out.println("Server connection established\n");
	 
			 	
		 
	
		 while(!str_userInput.equals("quit")) {
		
			 rec_acc = null;
			 str_userInput = "";
			try {
				
				
				System.out.println(acc);
				
				
				
				System.out.println("\nPlease enter command : ");
				str_userInput = userInput.nextLine();
				
				
				oos.writeObject(str_userInput); // 1
				oos.flush();
				
				
				if (str_userInput.equals("quit")) {
					
					continue;
					
				}
				
				requestCode = (int) is.readObject(); //4
				
				
				
				if (requestCode != 1) {
					System.out.println("\nServer determined that the command you entered was wrong ): \n");
					continue;
				}
				else {
					System.out.println("\nServer accepted your command\n");
					 
					oos.writeObject(acc); //5
					oos.flush();
					
					requestCode = (int) is.readObject();  //8
					
					if (requestCode != 1) {
						System.out.println("\nServer determined that an invalid account object was sent \n");
					}
					else {
						requestCode = (int) is.readObject();  //8
						
						if (requestCode != 1) {
							
							System.out.println("\n Something went wrong with the transaciton\n");
						}
						else {
							rec_acc = (Account) is.readObject();
							
							System.out.println("HERE IS YOUR ACCOUNT : \n\n" + rec_acc);
							System.out.println("\n\n Enjoy your day :) \n\n");
							
						
								acc = rec_acc;
								
								
							
						}
					}
					
					
					
					
				}
				
				
				
				
				
				
				
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(EOFException e) {
					System.out.println("Server has ended it's connection");
			}
			
		
		 
	
	}
			System.out.println("Ending connection to server...");
			is.close();
			oos.close();
			socket.close();
			System.out.println("Client connection with server ended");
	}
	

}
