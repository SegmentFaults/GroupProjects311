/* Michael Gentile, Connor Schmidt, Nick Hutchison, Travis Hawks
# Dr. Kiper
# CSE 311 Section A
# These tests were developed by Michael Gentile.
*/



import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPart1 {
	
	//this is to test the basic is prime function
	@Test
	public void testIsPrime() {
		assertTrue(Part1.isPrime(new BigInteger("2")));
		assertTrue(Part1.isPrime(new BigInteger("5")));
		assertTrue(Part1.isPrime(new BigInteger("11")));
		assertTrue(Part1.isPrime(new BigInteger("17")));
		assertTrue(Part1.isPrime(new BigInteger("29")));
		assertTrue(Part1.isPrime(new BigInteger("7919")));
	}
	//this is to test the square root function
	@Test
	public void testSqRoot() {
		assertTrue(Part1.sqRoot(new BigInteger("49")).equals(new BigInteger("7")));
		assertTrue(Part1.sqRoot(new BigInteger("25")).equals(new BigInteger("5")));
		assertTrue(Part1.sqRoot(new BigInteger("36")).equals(new BigInteger("6")));
		assertTrue(Part1.sqRoot(new BigInteger("64")).equals(new BigInteger("8")));
		assertTrue(Part1.sqRoot(new BigInteger("81")).equals(new BigInteger("9")));
		assertTrue(Part1.sqRoot(new BigInteger("100")).equals(new BigInteger("10")));
	}
	
	//this tests the precursor to find all the prime numbers in the list.
	
	@Test
	public void testFindPrimes() {
		BigInteger two = new BigInteger("2");
		BigInteger five = new BigInteger("5");
		BigInteger seven = new BigInteger("7");
		ArrayList<BigInteger> results = Part1.findPrimes(new BigInteger("10"));
		assertTrue(results.contains(two));
		assertTrue(results.contains(five));
		assertTrue(results.contains(seven));
		assertFalse(results.contains(new BigInteger("1")));
	}
	
	//testing find factors is the basis of our program
	//there is no need to test anything else because find factors
	//is the culminating effort of the rest.
	@Test
	public void testFindFactors() {
		BigInteger two = new BigInteger("2");
		BigInteger five = new BigInteger("5");
		BigInteger seven = new BigInteger("7");
		ArrayList<BigInteger> bigIntegerList = Part1.findPrimes(new BigInteger("10"));
		ArrayList<BigInteger> actualList = new ArrayList<BigInteger>();
		BigInteger[] primeNumbers= new BigInteger[bigIntegerList.size()];
		bigIntegerList.toArray(primeNumbers);
		Part1.findFactors(primeNumbers, actualList, new BigInteger("10"));
		assertTrue(actualList.contains(two));
		assertTrue(actualList.contains(five));
		assertFalse(actualList.contains(seven));
		assertFalse(actualList.contains(new BigInteger("1")));
	}
	/*
	 * We would test more but there is no easy or logical way to test the server implementations
	 * By doing this, we test everything but the connections, making this effective. 
	 */
}
