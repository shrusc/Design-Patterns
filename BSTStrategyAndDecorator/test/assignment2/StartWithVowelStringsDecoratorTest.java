package assignment2;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StartWithVowelStringsDecoratorTest {
	StartWithVowelStringsDecorator emptyDecorator; 
	BinarySearchTree<String> emptyTree; 
	BinarySearchTree<String> lexicallySortedTree; 
	StartWithVowelStringsDecorator vowelStrings; 
	String[] stringArray;

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

		//Create a BST ordered using SortByFirstCharacter strategy.
		lexicallySortedTree = new BinarySearchTree<String>(new SortByFirstCharacter());		
		for(index = 0; index < 6; index++){
			lexicallySortedTree.add(stringArray[index]);
		}
		emptyDecorator = new StartWithVowelStringsDecorator(emptyTree.iterator());
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 *Test the iterator decorator on empty String Iterator
	 *1. hasNext() should return false
	 *2. next() should throw an exception
	 */
	@Test(expected = NoSuchElementException.class)
	public void testEmptyIteratorDecorator() {
		assertFalse(emptyDecorator.hasNext());
		emptyDecorator.next();
	}

	/**
	 * 1. Construct a GetVowelStrings decorator on a string iterator
	 * 2. Test hasNext() on vowelStrings
	 * 3. Test next() and then check if equal to string object used in the creation. 
	 */
	@Test
	public void testIteratorDecorator() throws NoSuchElementException{

		//Case 1
		vowelStrings = new StartWithVowelStringsDecorator(lexicallySortedTree.iterator());

		//Case 2
		assertTrue(vowelStrings.hasNext());

		//Case 3
		assertEquals(stringArray[5], vowelStrings.next());
		assertTrue(vowelStrings.hasNext());

		assertEquals(stringArray[4], vowelStrings.next());
		assertTrue(vowelStrings.hasNext());
	}

}
