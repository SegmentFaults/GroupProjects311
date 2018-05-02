package de.tum.cs.i1.pse;



import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tum.cs.i1.pse.Context;
import de.tum.cs.i1.pse.MergeSort;
import de.tum.cs.i1.pse.Policy;
import de.tum.cs.i1.pse.QuickSort;
import de.tum.cs.i1.pse.SortStrategy;

public class FunctionalTest {
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
		
	@Test(timeout = 100)
	public void testIfPolicyIsMergeSort() {
		Context context = new Context();
		Policy policy = new Policy(context);
		
		boolean timeIsImportant = true;
		boolean spaceIsImportant = false;
		policy.configure(timeIsImportant, spaceIsImportant);
		SortStrategy implementation = context.getSortAlgorithm();
		assertTrue(implementation instanceof MergeSort);
	}
	
	@Test(timeout = 100)
	public void testIfPolicyIsQuickSort() {
		Context context = new Context();
		Policy policy = new Policy(context);
		boolean timeIsImportant = true;
		boolean spaceIsImportant = true;
		policy.configure(timeIsImportant, spaceIsImportant);
		SortStrategy implementation = context.getSortAlgorithm();
		assertTrue(implementation instanceof QuickSort);
	}
	
		
}
