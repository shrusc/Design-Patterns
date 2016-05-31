
package assignment1;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

/**
 * Junit class to test the methods of the BinarySearchTree class
 */
public class BinarySearchTreeTest {

	/**
	 * Method to test the constructor of BinarySearchTree
	 */
	@Test
	public void testBinarySearchTree() {
		BinarySearchTree<String> test = new BinarySearchTree<String>();
		assertEquals(0, test.countLeaves() );
	}

	/**
	 * Method for testing the insertion of a node to the BinarySearchTree
	 */
	@Test
	public void testInsert() {
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		assertEquals(0, binaryTree.countLeaves());
		binaryTree.insert("Cat");
		assertEquals(1, binaryTree.countLeaves());
		binaryTree.insert("apple");
		assertEquals(2, binaryTree.countLeaves());
		try {
			binaryTree.insert("Cat");	
			fail("Should raise an IllegalArgumentException");
		}
		catch (IllegalArgumentException success) {
			assertEquals("java.lang.IllegalArgumentException: Duplicate item Cat",success.toString());
		}
	}

	/**
	 * Method for testing the if the getElementsStartingWithVowelInReverseOrder() gives
	 * has all the elements of the tree starting with a vowel.
	 */
	@Test
	public void testCountLeaves() {
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		assertEquals(0, binaryTree.countLeaves());
		binaryTree.insert("Cat");
		assertEquals(1, binaryTree.countLeaves());
		binaryTree.insert("apple");
		assertEquals(2, binaryTree.countLeaves());
	}

	/**
	 * Method for testing the if the getElementsStartingWithVowelInReverseOrder() gives
	 * has all the elements of the tree starting with a vowel.
	 */
	@Test
	public void testLeavesStartingWithVowelInReverseOrder() {
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		binaryTree.insert("Owl");
		binaryTree.insert("Snake");
		binaryTree.insert("Lion");
		binaryTree.insert("Goat");
		binaryTree.insert("Elephant");
		binaryTree.insert("Ant");
		ArrayList<String> getDataWithVowels = binaryTree.leavesStartingWithVowelInReverseOrder();
		assertEquals("[Owl, Elephant, Ant]",getDataWithVowels.toString());
	}

	/**
	 * Method to test the BinarySearchTreeIterator and 
	 * functions hasNext and next
	 */
	@Test
	public void testIterator() throws UnsupportedOperationException {
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		Iterator<String> iterator;
		iterator = binaryTree.iterator();
		assertFalse(iterator.hasNext());
		binaryTree.insert("Cat");
		iterator = binaryTree.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("Cat", iterator.next());
		binaryTree.insert("ant");
		iterator = binaryTree.iterator();
		assertEquals("Cat",iterator.next());
		assertEquals("ant",iterator.next());
	}

	/**
	 * Method to test the remove function of the BinarySearchTreeIterator.
	 * remove - not handled, should result in a UnsupportedOperationException Exception 
	 */
	@Test
	public void testIteratorRemove() {
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		Iterator<String> iterator = binaryTree.iterator();
		binaryTree.insert("Cat");
		try {
			iterator.remove();	
			fail("Should raise an UnsupportedOperationException");
		}
		catch (UnsupportedOperationException success) {
			assertEquals("java.lang.UnsupportedOperationException: BinarySearchTreeIterator remove not supported",
					success.toString());
		}
	}

}
