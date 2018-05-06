#Strategy Pattern

Modified by Connor Schmidt
==========================

*Passes FunctionalTests
*Passes StructuralTests
*Client.main() runs


Changes from original:
----------------------

##Please note: 
	There were TODO items in (almost) every class that needed to be modified.
	The Context class did not exist and therefore did not have TODO items.
	The Context class was created through fixing compiler errors and failing tests.

##Context.java
	created the class
	implemented properties and methods to fix compiler errors and pass tests

##Client.java:
	main:
		created the sortingContext
		created the policy

##MergeSort.java:
	implemented the SortStrategy interface
	
##Policy.java:
	added a context property
	
	constructor:
		set the context
		
	configure:
		set the sorting strategy on the context
		
##QuickSort.java:
	implemented the SortStrategy interface
	
##SortStrategy.java:
	added the interface method "performSort" with int[] as a parameter
