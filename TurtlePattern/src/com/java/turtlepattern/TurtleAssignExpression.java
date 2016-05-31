package com.java.turtlepattern;

/*
 * Interpreter Expression class to evaluate an assign statement
 */
public class TurtleAssignExpression implements TurtleExpression{
	private int value;
	private String variableName;
	
	public TurtleAssignExpression(String variableName, int value) {
		this.value = value;
		this.variableName = variableName;
	}
	
	/*
	 * stores the value of the variable in context
	 */
	@Override
	public int evaluate(TurtleContext context) {
		context.setValue(variableName, value);
		return SUCCESS;
	}

	/*
	 * Calls the visit method of TurtleVisitor
	 * and passes itself as parameter
	 */
	public void accept(TurtleVisitor visitor){
		visitor.visitTurtleAssignExpression(this);
	}
	
	/**
	 * return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}
}
