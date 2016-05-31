
package assignment1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class which has the main and all the tests for the BST and the Node classes
 *
 */
public class Testing {

	/**
	 * Main Function
	 */
	public static void main(String[] args) {
		TestRunner.run(assignment1.Testing.suite());
		BinarySearchTree<String> binaryTree = new BinarySearchTree<String>();
		binaryTree.insert("Owl");
		binaryTree.insert("Snake");
		binaryTree.insert("Lion");
		binaryTree.insert("Goat");
		binaryTree.insert("Elephant");
		binaryTree.insert("Ant");
		System.out.println("Printing all the elements of the tree in alphabetical order");
		Iterator<String> iterator = binaryTree.iterator();
		while (iterator.hasNext()) {
			String x = iterator.next(); 
			System.out.println(x);
		}
		System.out.println();
		ArrayList<String> getVowels = binaryTree.leavesStartingWithVowelInReverseOrder();
		System.out.println("Elements of the tree starting with a vowel in reverse order");
		for(String item : getVowels)
			System.out.println(item);
	}

	/**
	 * Test Suite for the BinarySearchTreeTest and NodeTest classes
	 */
	static public TestSuite suite() {
		TestSuite suite= new TestSuite();
		try {
			suite.addTest(new JUnit4TestAdapter(BinarySearchTreeTest.class));
			suite.addTest(new JUnit4TestAdapter(NodeTest.class));
		} 
		catch (Exception e){
			System.out.println(e);
		}
		return suite;
	}	

}
