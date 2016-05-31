package assignment2;

import java.util.*;

public class NullNode<E> extends Node<E> {

	/**
	 * Creates a new binary node  
	 * @return the reference to this Node
	 */
	@Override
	protected Node<E> addNode(E data, Comparator<E> comparator){
		Node<E> newNode = new BinaryNode<E>(data);
		return newNode;
	}

	@Override
	protected boolean isNull() {
		return true;
	}

	/**
	 * @returns 0 - the size of a NullNode.
	 */
	@Override
	protected int size() {
		return 0;
	}

}