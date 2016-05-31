package com.java.turtlepattern;

/*
 * Interpreter class to evaluate penUp.
 */
public class TurtlePenUpExpression implements TurtleExpression {

	@Override
	public int evaluate(TurtleContext context) {
		context.getTurtle().penUp();
		return SUCCESS;
	}

	@Override
	public void accept(TurtleVisitor visitor){
		visitor.visitTurtlePenUpExpression(this);
	}

}
