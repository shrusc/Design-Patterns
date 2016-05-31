package com.java.turtlepattern;

/*
 * Command class to execute penDown.
 */
public class TurtlePenDownCommand extends TurtleCommand {
	private TurtleContext context;
	
	public TurtlePenDownCommand(TurtleContext context) {
		this.context = context;
	}
	
	/*
	 * Invokes penDown() on turtle.
	 */
	@Override
	public void execute() {
		context.getTurtle().penDown();
	}

	@Override
	public void undo() {
		context.getTurtle().penUp();
	}
}
