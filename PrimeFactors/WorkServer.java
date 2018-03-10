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

public class WorkServer {

	public static void main(String[] args) {

		final int threads = 4;
		
		if (args.length != 1) {
			System.err.println("Usage: java WorkServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try {
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Socket dispatcherSocket = serverSocket.accept();     
			ObjectOutputStream outToDispatcher = new ObjectOutputStream(dispatcherSocket.getOutputStream());                   
			ObjectInputStream inFromDispatcher = new ObjectInputStream(dispatcherSocket.getInputStream());
			
			Object inputObject;
			while ((inputObject = inFromDispatcher.readObject()) != null) {
				List<BigInteger> toCheck = (ArrayList<BigInteger>)inputObject;
				List<BigInteger> output = Collections.synchronizedList(new ArrayList<BigInteger>());
				
				FindPrimes finder = new FindPrimes(toCheck, output, 0);
				
				finder.findPrimes();
				
				/*int counter = 0;
				for(BigInteger num : toCheck) {
					if(counter >= threads) {
						counter = 0;
					}
					
					counter++;
					
				}*/
				
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
