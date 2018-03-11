import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;

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
	/*
	public static void recursion(ArrayList<BigInteger> bigIntegerList, ArrayList<BigInteger> actualList, BigInteger highValue) {
		System.out.println("===" + highValue + "===");
		for (int i = bigIntegerList.size() - 1; i >= 0; i--) {
			System.out.println(bigIntegerList.get(i));
			if (!highValue.equals(BigInteger.ONE) && highValue.mod(bigIntegerList.get(i)).equals(BigInteger.ZERO)) {
				actualList.add(bigIntegerList.get(i));
				highValue = highValue.divide(bigIntegerList.get(i));
				recursion(findPrimes(highValue), actualList, highValue);
			}
		}
		System.out.println("FINISHED " + highValue);
	}
	*/
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
	 
	/*public static BigInteger sqRoot(BigDecimal num) {
		BigDecimal approx1;
		BigDecimal approx2;
		approx1 = num;
		approx2 = new BigDecimal("1.0");
		BigDecimal two = new BigDecimal("2.0");
		BigDecimal delta = new BigDecimal("0.5");
		if (num.equals(BigDecimal.valueOf(740))) {
			System.out.println("HI");
		}
		while (  ( approx1.subtract(approx2)).abs().compareTo(delta) > 0 ) {
			System.out.println(( approx1.subtract(approx2)).abs().compareTo(delta));
			approx1 = (approx1.add(approx2).divide(two, 2));
			approx2 = (num.divide(approx1, 2));
		}
		return approx2.toBigInteger();
	}*/
	
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
