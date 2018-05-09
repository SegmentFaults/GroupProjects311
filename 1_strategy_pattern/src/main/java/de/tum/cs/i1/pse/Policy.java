/*
 * 5/7/2018
 * Source Project: https://github.com/togiberlin/java_design_pattern_koans/tree/exercises/2_behavioral_patterns/1_strategy_pattern
 * 
 * Edited by: Connor Schmidt schmidcc@miamioh.edu
 * Team: Michael Gentile, Travis Hawks, Nick Hutchison
 * 
 * Description:
 * Changes between the sorting method for the context based on parameters that are important to the implementer.
 * 
 */

package de.tum.cs.i1.pse;

public class Policy {

	// TODO: add the missing attribute
	private Context context;
	
	public Policy(Context context) {
		// set the context
		this.context = context;
	}

	public void configure(boolean timeIsImportant, boolean spaceIsImportant) {
		// TODO: add implementation
		
		SortStrategy strategy = null;
		
		if(timeIsImportant && spaceIsImportant) {
			// QuickSort
			strategy = new QuickSort();
		} else if (timeIsImportant && !spaceIsImportant) {
			// Merge Sort
			strategy = new MergeSort();
		}
		
		context.setSortAlgorithm(strategy);
	}
}
