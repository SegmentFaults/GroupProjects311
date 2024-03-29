import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 * A class to compute primes of some BigInteger input value.
 * DATE: 3/10/2018
 */
public class FindPrimes implements Callable<Void> {

	private List<BigInteger> input;
	private List<BigInteger> output;
	private int threadID;
	
	// Low is inclusive, high is exclusive
	public FindPrimes(List<BigInteger> input, List<BigInteger> output, int threadID) {
		this.input = input;
		this.output = output;
		this.threadID = threadID;
	}
	
	/**
	 * Method to call findPrimes()
	 */
	@Override
	public Void call() throws Exception {
		findPrimes();
		//System.out.println("Thread " + threadID + " done.");
		return null;
	}
	
	/**
	 * Iterates over the variable: input and determines of that index value isPrime.
	 * adds primes to output, else nothing.
	 */
	public void findPrimes() {
		for (BigInteger in : input) {
			if (isPrime(in)) {
				output.add(in);
			}
		}
	}

	
	/**
	 * A method to check whether a bigInteger is prime or not. Uses the square root method.
	 * @param check
	 * @return boolean
	 */
	boolean isPrime(BigInteger check) {
		BigDecimal bigdec = new BigDecimal(check);
		BigInteger incrementValue=new BigInteger("1");
		BigInteger squareRootValue = sqRoot(bigdec.toBigInteger());
		
		squareRootValue = squareRootValue.add(incrementValue);
	    for (BigInteger i = BigInteger.valueOf(2);
				i.compareTo(squareRootValue)<1;
				i=i.add(incrementValue)) {
	        	if (check.mod(i).equals(BigInteger.ZERO) && !check.equals(i)) {
		            return false;
		        }
	    }
	    return true;
	}
	
	// function adopted from
	// https://gist.github.com/JochemKuijpers/cd1ad9ec23d6d90959c549de5892d6cb
	public static BigInteger sqRoot(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = n.shiftRight(5).add(BigInteger.valueOf(8));
		while (b.compareTo(a) >= 0) {
			BigInteger mid = a.add(b).shiftRight(1);
			if (mid.multiply(mid).compareTo(n) > 0) {
				b = mid.subtract(BigInteger.ONE);
			} else {
				a = mid.add(BigInteger.ONE);
			}
		}
		return a.subtract(BigInteger.ONE);
	}
}
