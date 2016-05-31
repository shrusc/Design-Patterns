package assignment2;

import java.util.*;

public class BinaryNode<E> extends Node<E> {

	public BinaryNode(E data){
		this.nodeData = data;
		this.leftNode = new NullNode<E>();
		this.rightNode = new NullNode<E>();
	}

	/**
	 * Uses the comparator passed as the argument 
	 * to decide where the data needs to be added 
	 * @returns the reference to this Node
	 */
	@Override
	protected Node<E> addNode(E data, Comparator<E> comparator){
		if(comparator.compare(data, this.nodeData) < 0){				
			leftNode = leftNode.addNode(data,comparator);
		} 
		else  {
			rightNode = rightNode.addNode(data,comparator);
		}
		return this;	
	}

	@Override
	protected boolean isNull() {
		return false;
	}

	/**
	 * Adds the size of its left and right nodes 
	 * along with the size of this node
	 * @returns the size  
	 */
	@Override
	protected int size() {
		return 1 + leftNode.size() + rightNode.size();
	}
}
