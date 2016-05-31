package assignment2;

import java.util.Comparator;

/**
 * Abstract Node class that holds the data, 
 * left and right pointers. 
 */

public abstract class Node<E> {
	protected E   nodeData;
	protected Node<E> leftNode;      
	protected Node<E> rightNode;   

	public Node(){
		nodeData = null;
		leftNode = null;
		rightNode = null;
	}

	protected abstract Node<E> addNode(E data, Comparator<E> comparator);
	protected abstract boolean isNull();
	protected abstract int size();
}