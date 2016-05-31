package com.java.turtlepattern;

/*
 * Interpreter class to evaluate penDown.
 */

public class TurtlePenDownExpression implements TurtleExpression {
	@Override
	public int evaluate(TurtleContext context) {
		context.getTurtle().penUp();
		return SUCCESS;
	}

	@Override
	public void accept(TurtleVisitor visitor){
		visitor.visitTurtlePenDownExpression(this);
	}
}
