import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 *   This class is part of phase 4 and 5. This class is used to setup a number of servers that will
 *   Do the work / compute factors for the given numbers. For this project we used 4 work servers. 
 *   
 * Some code adopted from: https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 * DATE: 3/10/2018
 */
public class WorkServer {

	public static void main(String[] args) {

		final int threads = 4;
		
		//Confirming that the only received argument is the port number to listen too.
		if (args.length != 1) {
			System.err.println("Usage: java WorkServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		// Setups connection to the socket. Continues to accept inputs from the dispatcher
		// 		as arguments come in. 
		try {
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Socket dispatcherSocket = serverSocket.accept();     
			ObjectOutputStream outToDispatcher = new ObjectOutputStream(dispatcherSocket.getOutputStream());                   
			ObjectInputStream inFromDispatcher = new ObjectInputStream(dispatcherSocket.getInputStream());
			
			System.out.println("Listening");
			
			//Reads from socket
			Object inputObject;
			while ((inputObject = inFromDispatcher.readObject()) != null) {
				List<BigInteger> toCheck = (ArrayList<BigInteger>)inputObject;
				System.out.println("Found " + toCheck);
				
				List<BigInteger> output = Collections.synchronizedList(new ArrayList<BigInteger>());
				
				FindPrimes finder = new FindPrimes(toCheck, output, 0);
				
				//finds primes
				finder.findPrimes();
				
				outToDispatcher.writeObject(output);			
			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
