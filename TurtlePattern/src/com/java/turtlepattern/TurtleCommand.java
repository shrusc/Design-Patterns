package com.java.turtlepattern;

/*
 * Abstract command class.
 * Defines the basic operation to be performed by the commands
 */
public abstract class TurtleCommand {
	protected Turtle turtle;
	
	public abstract void execute();
	public abstract void undo();
}
