package com.java.turtlepattern;

/*
 * Interpreter Expression class to evaluate the value of a constant
 */
public class TurtleConstantExpression implements TurtleExpression {
	int constantValue;
	
	public TurtleConstantExpression(int value){
		constantValue = value;
	}
	
	/*
	 * Returns the value of the constant.
	 */
	@Override
	public int evaluate(TurtleContext context) {		
		return constantValue;
	}
	
	public void accept(TurtleVisitor visitor){
	}

}
