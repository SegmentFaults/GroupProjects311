/*
 * 5/7/2018
 * Source Project: https://github.com/togiberlin/java_design_pattern_koans/tree/exercises/2_behavioral_patterns/1_strategy_pattern
 * 
 * Edited by: Connor Schmidt schmidcc@miamioh.edu
 * Team: Michael Gentile, Travis Hawks, Nick Hutchison
 * 
 * Description:
 * Contains the Quick Sort implementation for the system.
 * 
 */

package de.tum.cs.i1.pse;

public class QuickSort implements SortStrategy {

	// TODO: add the missing interface

	private int[] a;
	private int n;

	public void performSort(int[] a) {
		this.a = a;
		n = a.length;
		if (n != 0)
			quicksort(0, n - 1);
	}

	private void quicksort(int lo, int hi) {
		int i = lo, j = hi;

		// compare element x
		int x = a[(lo + hi) / 2];

		// divide
		while (i <= j) {
			while (a[i] < x)
				i++;
			while (a[j] > x)
				j--;
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}

		// recursion
		if (lo < j)
			quicksort(lo, j);
		if (i < hi)
			quicksort(i, hi);
	}

	private void exchange(int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}
