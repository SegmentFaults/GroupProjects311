import java.net.*;
import java.io.*;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 * This class is the second class for phase 1. It will listen to a server and then respond with an echo
 * 		When the client sends over an argument. 
 *  Some code adopted from: https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 *  DATE: 3/10/2018
 */

public class EchoServer {
	public static void main(String[] args) throws IOException {

		//Logic check to confirm that 1 argument (port to use) is given.
		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		// Connecting to the socket, listening, and giving the echo response when viable. 
		try (
				ServerSocket serverSocket =
				new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();     
				PrintWriter out =
						new PrintWriter(clientSocket.getOutputStream(), true);                   
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				) {
			// appends  ">>>" to the actual client message
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				out.println(">>> " + inputLine);
			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}