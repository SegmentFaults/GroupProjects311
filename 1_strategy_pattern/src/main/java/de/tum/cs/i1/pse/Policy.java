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
