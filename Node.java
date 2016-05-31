package assignment2;

import java.util.Comparator;

/*
 * Node class that holds the data, left and right pointers. 
 */
public abstract class Node<E> {
	protected E   nodeData;
	protected Node<E> leftNode;      // Pointer to left node
	protected Node<E> rightNode;     // Pointer to right node
	protected abstract boolean addNode(E data,Node<E> parent,Comparator<E> orderer);
	protected abstract boolean isNull();
}