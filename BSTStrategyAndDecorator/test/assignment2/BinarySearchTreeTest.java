package assignment2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class BinarySearchTreeTest {
	BinarySearchTree<String> emptyTree; 
	BinarySearchTree<String> lexicallySortedTree; 
	BinarySearchTree<String> reverseLexicallySortedTree;
	String[] stringArray;
	String[] lexicallySorted = {"Elephant","Goat","Lion","Owl","Snake"};
	int[] sortedByGpaIndex = {2,0,1,4,3};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Create an array of string objects to be used to create the Tree.
	 */
	private void createStringArray() {
		stringArray =  new String[6];
		stringArray[0] = new String("Owl");
		stringArray[1] = new String("Snake");
		stringArray[2] = new String("Lion");
		stringArray[3] = new String("Goat");
		stringArray[4] = new String("Elephant");
		stringArray[5] = new String("Ant");
	}

	@Before
	public void setUp() throws Exception {
		int index = 0;
		createStringArray();

		emptyTree = new BinarySearchTree<String>(new SortByFirstCharacter());

		//Create a Tree ordered using SortByFirstCharacter strategy.
		lexicallySortedTree = new BinarySearchTree<String>(new SortByFirstCharacter());		

		for(index = 0; index < 5; index++){
			lexicallySortedTree.add(stringArray[index]);
		}

		//Create a Tree ordered using the SortByLastCharacter strategy.
		reverseLexicallySortedTree = new BinarySearchTree<String>(new SortByLastCharacter());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSize() {
		assertTrue(emptyTree.isEmpty());
		assertEquals(0,emptyTree.size());
		assertFalse(lexicallySortedTree.isEmpty());
		assertEquals(5,lexicallySortedTree.size());
	}

	/**
	 * 1. Tests successfully adding to the binary tree
	 * 2. Tests adding a duplicate entry to the tree 
	 *    in which case it should return a false.
	 */
	@Test
	public void testAddE() {
		//Case 1
		for(int index = 0; index < 6; index++){
			assertTrue(reverseLexicallySortedTree.add(stringArray[index]));
		}
		assertEquals(6,reverseLexicallySortedTree.size());

		//Case 2
		assertFalse(reverseLexicallySortedTree.add("Snake"));
		assertEquals(6,reverseLexicallySortedTree.size());
	}

	/**
	 * 1. Tests toString on empty Tree. Has to return "[]"
	 * 2. Tests the toString that at least it is
	 *    outputting " Binary Tree info:  [" 
	 *    followed by the first element of the tree
	 */
	@Test
	public void testToString() {
		String lexicalSortedString = lexicallySortedTree.toString();		    

		//Case 1.
		assertTrue(emptyTree.toString().contentEquals("[]")); 

		//Case 2.
		assertTrue(lexicalSortedString.contains("Binary Search Tree info: [Elephant, "));
	}

	/**
	 * 1. Tests the array size to be equal to Tree size
	 * 2. Checks the array elements to sorted order of string objects.
	 */
	@Test
	public void testToArray() {
		Object[] array = lexicallySortedTree.toArray();
		//Case 1
		assertTrue(array.length == lexicallySortedTree.size());

		//Case 2.
		for(int index = 0; index < 5; index++){
			assertEquals(lexicallySorted[index],array[index]);
		}	
	}

	/**
	 *Test the iterator on empty Tree
	 *1. hasNext should return false
	 *2. next() should throw an exception
	 */
	@Test(expected = NoSuchElementException.class)
	public void testEmptyTreeIterator(){
		Iterator<String> emptyTreeIterator = emptyTree.iterator();

		//Case 1 
		assertFalse(emptyTreeIterator.hasNext());

		//Case 2.
		emptyTreeIterator.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveFromIterator() {
		Iterator<String> treeIterator = lexicallySortedTree.iterator();
		treeIterator.remove();
	}

	/**
	 * 1. Test the iterator on lexicallySortedTree by checking if the 
	 * elements returned by next() is in the correct order
	 * 2. Add a new element to lexicallySortedTree. Then test next(). 
	 * Will throw ConcurrentModificationException
	 */ 
	@Test(expected = ConcurrentModificationException.class)
	public void testIterator() throws NoSuchElementException {
		Iterator<String> treeIterator = lexicallySortedTree.iterator();
		for(int index = 0; index < 5; index++){
			assertEquals(lexicallySorted[index],treeIterator.next());
		}
		lexicallySortedTree.add(stringArray[5]);
		treeIterator.next();
	}


}
