import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CSE 311 Project 2
 * @author Travis, Nick, Mike, Connor
 * This class is the second class for phase 1. It will listen to a server and then respond with an echo
 * 		A class that takes a user input of type BigIntegers and computes the factors of that input number.
 * 		Cout's the found factors.
 * DATE: 3/10/2018
 */
public class Part1 {
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		BigInteger highValue =(BigInteger) s.nextBigInteger();

		// Find all of the primes up to sqrt		
		ArrayList<BigInteger> bigIntegerList = findPrimes(highValue);
		ArrayList<BigInteger> actualList = new ArrayList<BigInteger>();
		BigInteger[] primeNumbers= new BigInteger[bigIntegerList.size()];
		bigIntegerList.toArray(primeNumbers);
		findFactors(primeNumbers, actualList, highValue);

		System.out.println(actualList);

	}

	//Method to compute factors. Takes in BigInteger array, an arraylist of BigInteger, and a BigInteger value
	public static void findFactors(BigInteger[] bigIntegerArray, ArrayList<BigInteger> actualList, BigInteger highValue) {
		System.out.println("highvalue + "+highValue);
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
					System.out.println(newValue);
					findFactors(bigIntegerArray, actualList, newValue);
					break;
				}
			}

		}
	}

	/**
	 * Takes a BigInteger, makes an arrayList of BigInteger type, and adds the computed primes to the arrayList.
	 * @param highValue
	 * @return ArrayList<BigInteger>
	 */
	public static ArrayList<BigInteger> findPrimes(BigInteger highValue) {
		ArrayList<BigInteger> bigIntegerList = new ArrayList<BigInteger>();
		BigDecimal bigdec = new BigDecimal(highValue);
		BigInteger squareRootValue = sqRoot(bigdec.toBigInteger());
		squareRootValue = squareRootValue.add(BigInteger.ONE);

		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(highValue) < 1; i=i.add(BigInteger.ONE)) {

			//for (BigInteger i = squareRootValue; i.compareTo(BigInteger.valueOf(2)) > -1; i=i.subtract(BigInteger.ONE)) {
			if (isPrime(i)) {
				bigIntegerList.add(i);
			} else {
				continue;
			}
		}

		return bigIntegerList;
	}

	/**
	 * A method called to check whether a BigInteger is prime.
	 * @param check 
	 * @return Boolean
	 */
	static boolean isPrime(BigInteger check) {
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
	/**
	 * 
	 * @param num is the BigDecimal number for which we are going to find the square root
	 * @return  the BigDecimal number which is an approximation to the square root of num
	 * 			The accuracy of this approximation is determined by the value of the delta
	 * 			This method uses an iterative method that is analogous to Netwton's method of finding roots.
	 * @author  James Kiper. PhD
	 * @date 	February 22, 2018
	 * 
	 */

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
	//function adopted from
	//https://gist.github.com/JochemKuijpers/cd1ad9ec23d6d90959c549de5892d6cb
}
