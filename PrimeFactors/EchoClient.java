import java.io.*;
import java.net.*;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 * The EchoClient class allows a client to connect to a server, send a string to the server, and
 * 		the server will respond with an echo command.
 * Some code adopted from: https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
 * DATE: 3/10/2018
 */
public class EchoClient {
	public static void main(String[] args) {

		// Logical check to confirm 2 arguments were received.
		if (args.length != 2) {
			System.err.println(
					"Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		// Connecting the socket and sending the user input to the server through the socket
		try (
				Socket echoSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
				)
		{
			int x = 0;

			while (x<50) {
				//out.println(userInput);
				x++;
			}
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}

	} //End Main
} //End Class
