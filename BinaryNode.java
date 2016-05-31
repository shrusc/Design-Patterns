package assignment2;

import java.util.*;

/*
 * Class representing the Binary node of the BST
 */
public class BinaryNode<E> extends Node<E> {		
	BinaryNode(E data){
		this.nodeData = data;
		this.leftNode = new NullNode<E>();
		this.rightNode = new NullNode<E>();
	}
	public boolean addNode(E data, Node<E> parent, Comparator<E> orderer){
		if(orderer.compare(data, this.nodeData) == 0) 
			return false;
		if(orderer.compare(data, this.nodeData) < 0){				
			 leftNode.addNode(data,leftNode,orderer);
		} else  {
			 rightNode.addNode(data,rightNode,orderer);
		}
		return true;	
	}
	@Override
	public boolean isNull() {
		return false;
	}
}
