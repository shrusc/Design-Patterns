package com.java.turtlepattern;

/*
 * Interpreter class to evaluate turn
 */
public class TurtleTurnExpression implements TurtleExpression {
	private TurtleExpression operand;
	
	public TurtleTurnExpression(TurtleExpression operand){
		this.operand = operand;
	}
	
	@Override
	public int evaluate(TurtleContext context) {		
		context.getTurtle().turn(operand.evaluate(context));
		return SUCCESS;
	}
	
	/*
	 * Returns the angle/direction the turtle has to turn
	 */
	public int getDirection(TurtleContext context){
		return operand.evaluate(context);
	}

	/*
	 * Calls the visit method of TurtleVisitor
	 * and passes itself as parameter
	 */
	@Override
	public void accept(TurtleVisitor visitor){
		visitor.visitTurtleTurnExpression(this);
	}
}
