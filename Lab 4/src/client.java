import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.seneca.accounts.*;
public class client {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Waiting for server connection...");
		
		Account acc = null;
		String str_userInput = null;
		Scanner userInput = new Scanner(System.in);
		
		Socket socket = new Socket("localhost", 4999);
		
		OutputStream output = socket.getOutputStream();
		InputStream input = socket.getInputStream();
		
		PrintWriter writer = new PrintWriter(output, true);
		
		
		System.out.println("Server connection established");
		
		System.out.println("Please enter Account ID : ");
		str_userInput = userInput.nextLine();
		
		writer.println("get|" + str_userInput);
		
	}

}
