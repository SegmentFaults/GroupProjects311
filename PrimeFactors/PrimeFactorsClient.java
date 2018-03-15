import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 * A class to establish the client part of the project. Accepts a user input, creates a socket connection,
 * 		and sends the input to the dispatcher.
 *  Some code adopted from: https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 *  DATE: 3/10/2018
 */

public class PrimeFactorsClient {

	public static void main(String[] args) {
		// Confirms that only 2 arguments are given. dispatcher IP and port to use.
		if (args.length != 2) {
			System.err.println(
					"Usage: java PrimeFactors <dispatcher ip> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		// Establishes port/socket connection.
		try (
				Socket echoSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
			) 
		{
			//Gets user input
			String userInput;
			while (true) {
				System.out.print("Enter a number: ");
				userInput = stdIn.readLine();
				
				//Sends user input through socket
				out.println(userInput);
				
				//Formatting pre answer look.
				System.out.println("echo: " + in.readLine());
				System.out.println("Calculated: " + in.readLine());
				System.out.println("====================");
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}

}
