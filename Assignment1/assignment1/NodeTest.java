
package assignment1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Junit class to test the methods of the Node class
 */
public class NodeTest {

	/**
	 * Method to test the constructor of Node
	 */
	@Test
	public void testNode() {
		Node<String> test = new Node<String>("Elephant",null,null);
		assertEquals("Elephant", test.getData() );
	}

	/**
	 * Method to test if the data on a node object can be set 
	 * and retrieved correctly
	 */
	@Test
	public void testSetGetData() {
		Node<String> test = new Node<String>("Elephant",null,null);
		assertEquals("Elephant", test.getData() );
		test.setData("Owl");
		assertEquals("Owl", test.getData() );
	}

	/**
	 * Method to test the functionality of setting a node as the right 
	 * node of another node and getting the right node of a node.
	 */
	@Test
	public void testSetGetRight() {
		Node<String> nodeOne = new Node<String>("Elephant",null,null);
		Node<String> nodeTwo = new Node<String>("Owl",null,null);
		nodeOne.setRight(nodeTwo);
		assertEquals("Owl", nodeOne.getRight().getData() );
	}

	/**
	 * Method to test the functionality of setting a node as the left 
	 * node of another node and getting the left node of a node
	 */
	@Test
	public void testSetGetLeft() {
		Node<String> nodeOne = new Node<String>("Elephant",null,null);
		Node<String> nodeTwo = new Node<String>("Owl",null,null);
		nodeOne.setLeft(nodeTwo);
		assertEquals("Owl", nodeOne.getLeft().getData() );
	}

	/**
	 * Method to test the functionality dataStartsWithVowel which
	 * Returns true if the data of a node is a String object 
	 * and starts with a vowel, returns false otherwise.
	 */
	@Test
	public void testDataStartsWithVowel() {
		Node<String> nodeOne = new Node<String>("Elephant",null,null);
		assertTrue(nodeOne.dataStartsWithVowel());
		Node<String> nodeTwo = new Node<String>("Bat",null,null);
		assertFalse(nodeTwo.dataStartsWithVowel());
	}

}
