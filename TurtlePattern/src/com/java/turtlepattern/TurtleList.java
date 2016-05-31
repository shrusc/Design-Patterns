package com.java.turtlepattern;

import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Extends the linked list class to add override the iterator
 * to turtleIterator type.
 */
public class TurtleList<TurtleCommand> extends LinkedList<TurtleCommand> {
	/* 
	 * Returns a filter ListIterator of the elements in list
	 * starting at the specified position.
	 */
	@Override
	public ListIterator<TurtleCommand> listIterator() {		
		return  (ListIterator<TurtleCommand>) new TurtleIterator((TurtleList<com.java.turtlepattern.TurtleCommand>) this);
	}

}
