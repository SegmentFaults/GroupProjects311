import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeFactors {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("High Value: ");
		BigInteger highValue = (BigInteger) s.nextBigInteger();
	
		System.out.print("Threads per Server: ");
		
		int threads = s.nextInt(); 
				
		/*for(int i = maxThreads; i >= 1; i /= 2) {
			System.out.println("====================");
			dispatchNumbers(i, highValue);
		}*/
		
		dispatchNumbers(threads, highValue);

		System.out.println("Done");
	}
	
	public static void dispatchNumbers(int threads, BigInteger highValue) {
		System.out.println("Threads: " + threads);
		List<BigInteger> bigIntegerList = Collections.synchronizedList(new ArrayList<BigInteger>());
				
		List<ArrayList<BigInteger>> intSets = new ArrayList<ArrayList<BigInteger>>();
				
		Collection<FindPrimes> allThreads = new ArrayList<FindPrimes>();

		for(int i = 0; i < threads; i++) {
			intSets.add(new ArrayList<BigInteger>());
			
			FindPrimes thread = new FindPrimes(intSets.get(i), bigIntegerList, i);
			
			allThreads.add(thread);
		}
		
		// This loses efficiency because the last thread is going to be doing extra work (from leftover numbers). 
		// Also, big numbers are not spread evenly across all threads.
		int counter = 0;
		for(BigInteger start = BigInteger.valueOf(2); start.compareTo(highValue) < 1; start = start.add(BigInteger.ONE)) {
			//4 threads: 0, 1, 2, 3, 0, 1, 2, 3
			if(counter >= threads) {
				counter = 0;
			}
			
			//System.out.println(counter);
			
			intSets.get(counter).add(start);
			
			counter++;
		}
				
        ExecutorService es = Executors.newFixedThreadPool(threads);

    	long startTime = System.currentTimeMillis();
        
        try {
			es.invokeAll(allThreads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
        es.shutdown();
                       
        try {
			es.awaitTermination(60, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("finished finding primes in " + (endTime - startTime) + " millis");
        
        Collections.sort(bigIntegerList);
        //System.out.println("All primes " + bigIntegerList);
        
		ArrayList<BigInteger> actualList = new ArrayList<BigInteger>();
		BigInteger[] primeNumbers= new BigInteger[bigIntegerList.size()];
		bigIntegerList.toArray(primeNumbers);
		findFactors(primeNumbers, actualList, highValue);
		System.out.println("Prime Factorization " + actualList);
	}

	public static void findFactors(BigInteger[] bigIntegerArray, ArrayList<BigInteger> actualList, BigInteger highValue) {
		//System.out.println("highvalue + "+highValue);
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