package assignment1;

import java.util.*;

/**
 * A binary search tree class with insertion and lookup. 
 * The natural order of tree elements is used during insertion.  
 * Also, has functionality of providing the data of the nodes, that start 
 * with a vowel in a ArrayList.
 * Can be extended later to work with a user defined comparator.
 */

public class BinarySearchTree <T extends Comparable<T>> {

	/**
	 * Root of the tree.Will be null for an empty tree.
	 */
	private Node<T> root;

	/**
	 * ArrayList which has the leaves of the tree which start with a vowel 
	 * in reverse alphabetical order
	 */
	private ArrayList<T> elementsStartingWithVowelInReverse =  new ArrayList<T>();

	/**
	 * Constructs an empty BST that can only accept Comparables as nodes.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Best to put the comparison code in a single place
	 * @param x - data present
	 *        y - input to compare
	 * @returns 0 - if both x and y are the same
	 *         1 - if y is greater than x
	 *        -1 - if y is lesser than x
	 */        
	private int compare(T x, T y) {
		return x.compareTo(y);
	}

	/**
	 * Adds a single data node to the tree.  If there is already an
	 * node in the tree that has data which compares equal to the item being inserted,
	 * then an IllegalArgumentException is thrown.
	 * @param data - data to be inserted
	 * @throws - IllegalArgumentException if there is a duplicate entry
	 */
	public void insert(T data) {
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> parent, T dataToInsert) throws IllegalArgumentException {
		if (parent == null)
			return new Node<T>(dataToInsert,null,null);

		if (compare(dataToInsert, parent.getData()) == 0)
			throw new IllegalArgumentException("Duplicate item "+ dataToInsert.toString());

		if (compare(dataToInsert, parent.getData()) < 0)
			parent.setLeft(insert(parent.getLeft(), dataToInsert));
		else
			parent.setRight(insert(parent.getRight(), dataToInsert));

		return parent;
	}

	/**
	 * Counts the number of nodes in the tree 
	 * @returns - the number of nodes
	 */
	public int countLeaves() {
		return countLeaves(root);
	}

	private int countLeaves(Node<T> parent) {
		if(parent == null) return 0;
		else
			if(parent.getLeft() == null && parent.getRight() == null) return 1;
			else
				return countLeaves(parent.getLeft()) + countLeaves(parent.getRight()) + 1;
	}

	/**
	 * Method which gets all the nodes which have data strings,
	 * starting with a vowel in reverse alphabetical order.
	 * @returns - starting with a vowel in reverse alphabetical order
	 */
	public ArrayList<T> leavesStartingWithVowelInReverseOrder() {
		return leavesStartingWithVowelInReverseOrder(root);
	}

	private ArrayList<T> leavesStartingWithVowelInReverseOrder(Node<T> root) {	
		if (root == null) 
			return null;
		leavesStartingWithVowelInReverseOrder(root.getRight());
		if(root.dataStartsWithVowel())
			elementsStartingWithVowelInReverse.add(root.getData());
		leavesStartingWithVowelInReverseOrder(root.getLeft());
		return elementsStartingWithVowelInReverse;
	}

	/**
	 * Method which returns the iterator over the tree,
	 * for an in-order traversal.
	 * @returns - Iterator for the BST
	 */
	public Iterator<T> iterator() {
		return new BinarySearchTreeIterator();
	}

	/**
	 * Private Iterator class for the BST,
	 * for an in-order traversal.
	 */
	private class BinarySearchTreeIterator implements Iterator<T> {
		private Stack<Node<T>> stack = new Stack<>();
		private Node<T> current;

		/**
		 * Constructs an empty BST iterator 
		 */
		public BinarySearchTreeIterator() {
			current = root;
		}

		/**
		 * @returns - true if next node present else false
		 */
		public boolean hasNext() {
			return (!stack.isEmpty() || current != null);
		}

		/**
		 * next method 1st traverses through the left sub-tree and then 
		 * goes through the right sub-tree each time updating the current node
		 * for an in-order traversal.
		 * @returns - data of the node
		 */
		public T next(){
			while (current != null) {
				stack.push(current);
				current = current.getLeft();
			}
			current = stack.pop();
			Node<T> node = current;
			current = current.getRight();
			return node.getData();
		}

		public void remove() {
			throw new UnsupportedOperationException("BinarySearchTreeIterator remove not supported");
		}
	}
}

