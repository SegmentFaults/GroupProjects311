import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Dispatcher {

	static ObjectOutputStream outToWorker1;
	static ObjectOutputStream outToWorker2;
	static ObjectOutputStream outToWorker3;
	static ObjectOutputStream outToWorker4;

	static ObjectInputStream inFromWorker1;
	static ObjectInputStream inFromWorker2;
	static ObjectInputStream inFromWorker3;
	static ObjectInputStream inFromWorker4;
	
	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: java Dispatcher <listening port number> <server 1 ip> <server 2 ip> <server 3 ip> <server 4 ip> <server port>");
			System.exit(1);
		}
		
		String hostName1 = args[1];
		String hostName2 = args[2];
		String hostName3 = args[3];
		String hostName4 = args[4];
		
		int listeningPort = Integer.parseInt(args[0]);
		int serverPort = Integer.parseInt(args[5]);
		
		try 
		{
			// client socket; dispatcher -> client
			// dispatcher socket: client -> dispatcher
			
			// server socket: dispatcher -> server
			// dispatcherClient socket: server -> dispacherr
			
			ServerSocket dispatcherSocket = new ServerSocket(Integer.parseInt(args[0])); //listening socket
			
			Socket clientSocket = dispatcherSocket.accept(); //incoming client connection
			
			PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true); // output to client
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // input from client
			
			// From EchoClient
			Socket workerSocket1 = new Socket(hostName1, serverPort);
			Socket workerSocket2 = new Socket(hostName2, serverPort);
			Socket workerSocket3 = new Socket(hostName3, serverPort);
			Socket workerSocket4 = new Socket(hostName4, serverPort);
			
		    outToWorker1 = new ObjectOutputStream(workerSocket1.getOutputStream());
			outToWorker2 = new ObjectOutputStream(workerSocket2.getOutputStream());
			outToWorker3 = new ObjectOutputStream(workerSocket3.getOutputStream());
			outToWorker4 = new ObjectOutputStream(workerSocket4.getOutputStream());

			inFromWorker1 = new ObjectInputStream(workerSocket1.getInputStream());
			inFromWorker2 = new ObjectInputStream(workerSocket2.getInputStream());
			inFromWorker3 = new ObjectInputStream(workerSocket3.getInputStream());
			inFromWorker4 = new ObjectInputStream(workerSocket4.getInputStream());
			
			String inputLine;
			while ((inputLine = inFromClient.readLine()) != null) {
				BigInteger value = new BigInteger(inputLine);				
				outToClient.println("Calculating stuff for " + value);
				
				List<BigInteger> factors = primeFactorization(value);
				
				outToClient.println(factors);
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
			System.exit(1);
		}  catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + listeningPort + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
	
	public static List<BigInteger> primeFactorization(BigInteger highValue) {
		final int threads = 4;
		
		List<BigInteger> allOutputs = new ArrayList<BigInteger>();
		List<ArrayList<BigInteger>> integerSets = new ArrayList<ArrayList<BigInteger>>();
				
		for(int i = 0; i < threads; i++) {
			integerSets.add(new ArrayList<BigInteger>());
		}
		
		int counter = 0;
		for(BigInteger start = BigInteger.valueOf(2); start.compareTo(highValue) < 1; start = start.add(BigInteger.ONE)) {			
			if(counter >= threads) {
				counter = 0;
			}
						
			integerSets.get(counter).add(start);
			
			counter++;
		}
		
		try {
			outToWorker1.writeObject(integerSets.get(0));
			outToWorker2.writeObject(integerSets.get(1));
			outToWorker3.writeObject(integerSets.get(2));
			outToWorker4.writeObject(integerSets.get(3));

			
			List<BigInteger> worker1Output = (ArrayList<BigInteger>)inFromWorker1.readObject();
			List<BigInteger> worker2Output = (ArrayList<BigInteger>)inFromWorker2.readObject();
			List<BigInteger> worker3Output = (ArrayList<BigInteger>)inFromWorker3.readObject();
			List<BigInteger> worker4Output = (ArrayList<BigInteger>)inFromWorker4.readObject();

			allOutputs.addAll(worker1Output);
			allOutputs.addAll(worker2Output);
			allOutputs.addAll(worker3Output);
			allOutputs.addAll(worker4Output);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		
		Collections.sort(allOutputs);
		ArrayList<BigInteger> actualList = new ArrayList<BigInteger>();
		BigInteger[] primeNumbers= new BigInteger[allOutputs.size()];
		allOutputs.toArray(primeNumbers);
		findFactors(primeNumbers, actualList, highValue);
		
		return actualList;
	}

	public static void findFactors(BigInteger[] bigIntegerArray, ArrayList<BigInteger> actualList, BigInteger highValue) {
		boolean found = false;
		
		for (int x=0; x<bigIntegerArray.length; x++) {
			if(bigIntegerArray[x].equals(highValue)) {
				BigInteger temp = new BigInteger(bigIntegerArray[x].toString());
				actualList.add(temp);
				found=true;
				break;
			}
		}
		
		if(!found){
			for (int i = 0; i < bigIntegerArray.length && bigIntegerArray[i].compareTo(highValue) < 1; i++) {
				if (highValue.mod(bigIntegerArray[i]).equals(BigInteger.ZERO)) {
					BigInteger newValue = new BigInteger(highValue.divide(bigIntegerArray[i]).toString());
					
					actualList.add(bigIntegerArray[i]);
					
					//System.out.println(newValue);
					findFactors(bigIntegerArray, actualList, newValue);
					
					break;
				}
			}
			
		}
	}
	
}
