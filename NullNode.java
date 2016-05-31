package assignment2;

import java.util.*;

public class NullNode<E> extends Node<E> {
	NullNode(){
		
	}
	public boolean addNode(E data, Node<E> parent, Comparator<E> orderer){
		parent = new BinaryNode<E>(data);
		System.out.println("Inserting "+parent.nodeData);
		return true;
	}
	@Override
	public boolean isNull() {
		return true;
	}
	
}