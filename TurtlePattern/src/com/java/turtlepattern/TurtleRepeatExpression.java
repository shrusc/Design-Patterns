package com.java.turtlepattern;

import java.util.ArrayList;

/*
 * 
 */
public class TurtleRepeatExpression implements TurtleExpression {
	private ArrayList<TurtleExpression> repeatExpressionList;
	private TurtleExpression operand;
	
	public TurtleRepeatExpression(TurtleExpression operand, ArrayList<TurtleExpression> repeatExpressionList){
		this.repeatExpressionList = repeatExpressionList;
		this.operand =  operand;
	}
	
	/*
	 * Evaluates all the statements in repeat block
	 */
	public int evaluate(TurtleContext context){
		int times = operand.evaluate(context);
		
		for(int index = 1; index <= times; index++){
			for(int listIndex = 0; listIndex < repeatExpressionList.size(); listIndex++){
				repeatExpressionList.get(listIndex).evaluate(context);
			}
		}
		return SUCCESS;
	}
	
	@Override
	public void accept(TurtleVisitor visitor) {
		visitor.visitTurtleRepeatExpression(this);					
	}

	/*
	 * Invokes accept() on all the interpreter objects in the list
	 * so as to obtain the command list by visiting each object
	 */
	public void acceptOnCommandList(int times, TurtleVisitor visitor){
		for(int index = 1; index <= times; index++){
			for(int listIndex = 0; listIndex < repeatExpressionList.size(); listIndex++){
				repeatExpressionList.get(listIndex).accept(visitor);
			}
		}
	}
	
	/*
	 * Returns the number of times the repeat block has to be executed.
	 */
	public int getTimesToRepeat(TurtleContext context) {		
		return operand.evaluate(context);
	}
	
}
