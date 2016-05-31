package com.java.turtlepattern;

/*
 * Command class to execute turn.
 */
public class TurtleTurnCommand extends TurtleCommand {
	private int degree;
	private TurtleContext context;
	TurtleTurnExpression turnExpression;
	
	public TurtleTurnCommand(TurtleContext context,TurtleTurnExpression turnExpression) {
		this.context = context;
		this.turnExpression = turnExpression;		
	}
	
	@Override
	public void execute() {
		this.degree = turnExpression.getDirection(context);
		context.getTurtle().turn(degree);
	}

	@Override
	public void undo() {
		context.getTurtle().turn(-degree);
	}

}
