/*
 * 5/7/2018
 * Source Project: https://github.com/togiberlin/java_design_pattern_koans/tree/exercises/2_behavioral_patterns/1_strategy_pattern
 * 
 * Created by: Connor Schmidt schmidcc@miamioh.edu
 * Team: Michael Gentile, Travis Hawks, Nick Hutchison
 * 
 * Description:
 * Performs the sort for the system.
 * 
 */

package de.tum.cs.i1.pse;

public class Context {

	private int[] array;
	private SortStrategy sortAlgorithm;
	
	public Context() {
		sortAlgorithm = new QuickSort();
	}
	
	public SortStrategy getSortAlgorithm() {
		return sortAlgorithm;
	}
	
	public void setArray(int[] array) {
		this.array = array;
	}
	
	public void sort() {
		sortAlgorithm.performSort(array);
	}
	
	public void setSortAlgorithm(SortStrategy strategy) {
		sortAlgorithm = strategy;
	}	
}
