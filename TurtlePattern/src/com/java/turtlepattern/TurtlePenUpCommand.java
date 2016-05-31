package com.java.turtlepattern;

/*
 * Command class to execute penUp.
 */
public class TurtlePenUpCommand extends TurtleCommand {
	private TurtleContext context;
	
	public TurtlePenUpCommand(TurtleContext context) {
		this.context = context;
	}
	@Override
	public void execute() {
		context.getTurtle().penUp();
	}

	@Override
	public void undo() {
		context.getTurtle().penDown();
	}

}
