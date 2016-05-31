package com.java.turtlepattern;

/*
 * Interface defining an interpreter expression for turtle program
 */
public interface TurtleExpression {
	final int SUCCESS = 1;
	public int evaluate( TurtleContext context ); 
	public void accept(TurtleVisitor visitor);
}
