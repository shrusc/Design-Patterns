package com.java.turtlepattern;

/*
 * Interpreter Expression class to evaluate an move statement
 */

public class TurtleMoveExpression implements TurtleExpression {
	private TurtleExpression operand;
	
	public TurtleMoveExpression(TurtleExpression operand){
		this.operand = operand;
	}
	
	/*
	 *Invokes move() operation on turtle
	 */
	@Override
	public int evaluate(TurtleContext context) {		
		context.getTurtle().move(operand.evaluate(context));
		return SUCCESS;
	}
	
	/*
	 * Evaluates the operand to get the distance to be moved/
	 */
	public int getDistance(TurtleContext context) {
		return operand.evaluate(context);
	}
	
	/*
	 * Calls the visit method of TurtleVisitor
	 * and passes itself as parameter
	 */
	@Override
	public void accept(TurtleVisitor visitor){
		visitor.visitTurtleMoveExpression(this);
	}

}
