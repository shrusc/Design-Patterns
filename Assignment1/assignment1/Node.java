
package assignment1;

/**
 * The binary search tree is built using this node class. 
 * Each node stores one data element, and has left and right 
 * sub-tree pointer which may be null.  
 */

public class Node<T>{
	private T data;
	private Node<T> left;
	private Node<T> right;

	/**
	 * Constructor 
	 * @param dataToSet - data to be assigned to the node
	 * @param rightNode - the right node of the constructed node
	 * @param leftNode - the left node of the constructed node
	 * @return - the node object constructed
	 */
	public Node(T dataToInsert, Node<T> rightNode, Node<T> leftNode) {
		data = dataToInsert;
		left = leftNode;
		right = rightNode; 
	}

	/**
	 * Sets the data of the node 
	 * @param dataToSet - data to be set to the node
	 */
	public void setData(T dataToSet) {
		data = dataToSet;
	}

	/**
	 * Returns the data of the node 
	 * @return - data of the node
	 */
	public T getData(){
		return data;
	}

	/**
	 * Returns the right linked node of the current node 
	 * @return - the right linked node of the current node
	 */
	public Node<T> getRight() {
		return right;
	}

	/**
	 * Sets the right node of the current node 
	 * @param - the node to be linked right to the current node
	 */
	public void setRight(Node<T> rightNode){
		right = rightNode;
	}

	/**
	 * Returns the left linked node of the current node 
	 * @return - the left linked node of the current node
	 */
	public Node<T> getLeft(){
		return left;
	}

	/**
	 * Sets the left node of the current node 
	 * @param - the node to be linked left to the current node
	 */
	public void setLeft(Node<T> leftNode){
		left = leftNode;
	}

	/**
	 * Method to check if the data of a node starts with a vowel
	 * @return - true if the data of a node is a String object 
	 * and starts with a vowel, false otherwise.
	 */
	public boolean dataStartsWithVowel() {
		if(data.getClass().equals(String.class)) {
			char letter = data.toString().charAt(0);
			if(letter == 'A' || letter == 'E' || letter == 'I' ||
					letter == 'O' || letter == 'U' || letter == 'a' ||
					letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u')
				return true;
			return false;
		}
		return false;
	}

}
