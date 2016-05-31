package com.java.turtlepattern;

/*
 * Interpreter class to evaluate a variable.
 */

public class TurtleVariableExpression implements TurtleExpression {
	private String variableName; 
	
	public TurtleVariableExpression( String variableName ){
		this.variableName = variableName;
	} 
	
	/*
	 * Returns the value of the variable by fetching it from context
	 */
	@Override
	public int evaluate(TurtleContext context) {
		return context.getValue(variableName);
	}

	@Override
	public void accept(TurtleVisitor visitor) {
		
	}
}
