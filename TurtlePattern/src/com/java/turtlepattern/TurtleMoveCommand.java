package com.java.turtlepattern;

/*
 * Command class to execute move statement
 */
public class TurtleMoveCommand extends TurtleCommand {
	private TurtleMoveExpression moveExpression;
	private TurtleContext context;
	private int distance;
	
	public TurtleMoveCommand(TurtleContext context,TurtleMoveExpression moveExpression) {
		this.context = context;
		this.moveExpression = moveExpression;
	}
	
	/*
	 * Executes move() operation on turtle.
	 */
	@Override
	public void execute() {
		this.distance = moveExpression.getDistance(context);
		context.getTurtle().move(distance);
	}

	/*
	 * Executes move() operation on turtle with negated distance
	 * so as to move back to previous position
	 */
	@Override
	public void undo() {
		context.getTurtle().move(-distance);
	}

}
