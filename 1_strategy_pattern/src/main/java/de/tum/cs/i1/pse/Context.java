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
