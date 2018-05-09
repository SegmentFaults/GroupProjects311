/*
 * 5/7/2018
 * Source Project: https://github.com/togiberlin/java_design_pattern_koans/tree/exercises/2_behavioral_patterns/1_strategy_pattern
 * 
 * Edited by: Connor Schmidt schmidcc@miamioh.edu
 * Team: Michael Gentile, Travis Hawks, Nick Hutchison
 * 
 * Description:
 * Contains the Merge Sort implementation for the system.
 * 
 */

package de.tum.cs.i1.pse;

public class MergeSort implements SortStrategy {

	// TODO: add the missing interface

	/***********************************************************************
	 * Merge the subarrays a[lo] .. a[mid-1] and a[mid] .. a[hi-1] into a[lo] ..
	 * a[hi-1] using the auxilliary array aux[] as scratch space.
	 *
	 * Precondition: the two subarrays are in ascending order Postcondition:
	 * a[lo] .. a[hi-1] is in ascending order
	 *
	 ***********************************************************************/
	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		int i = lo, j = mid;
		for (int k = lo; k < hi; k++) {
			if (i == mid)
				aux[k] = a[j++];
			else if (j == hi)
				aux[k] = a[i++];
			else if (a[j] < a[i])
				aux[k] = a[j++];
			else
				aux[k] = a[i++];
		}

		// copy back
		for (int k = lo; k < hi; k++)
			a[k] = aux[k];
	}

	/***********************************************************************
	 * Mergesort the subarray a[lo] .. a[hi-1], using the auxilliary array aux[]
	 * as scratch space.
	 ***********************************************************************/
	private static void sort(int[] a, int[] aux, int lo, int hi) {

		// base case
		if (hi - lo <= 1)
			return;

		// sort each half, recursively
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid, hi);

		// merge back together
		merge(a, aux, lo, mid, hi);
	}

	/***********************************************************************
	 * Sort the array a using mergesort
	 ***********************************************************************/
	public void performSort(int[] a) {
		int N = a.length;
		int[] aux = new int[N];
		sort(a, aux, 0, N);
	}

}
