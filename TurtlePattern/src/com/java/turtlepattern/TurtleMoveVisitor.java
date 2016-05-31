package com.java.turtlepattern;

/*
 * Visits the interpreter objects and calculates the total
 * distance moved by adding the operand value of move.
 */

public class TurtleMoveVisitor extends TurtleVisitor{
	private int distanceMoved;
	
	public TurtleMoveVisitor(TurtleContext context){
		this.context = context;
	}
	
	@Override
	public void visitTurtleMoveExpression(TurtleMoveExpression moveExpression) {
		distanceMoved += moveExpression.getDistance(context);		
	}

	@Override
	public void visitTurtleTurnExpression(TurtleTurnExpression turnExpression) {
		
	}

	@Override
	public void visitTurtlePenUpExpression(TurtlePenUpExpression penUpExpression) {
		
	}

	@Override
	public void visitTurtlePenDownExpression(
			TurtlePenDownExpression penDownExpression) {
	}

	@Override
	public void visitTurtleAssignExpression(
			TurtleAssignExpression assignExpression) {
		context.setValue(assignExpression.getVariableName(), assignExpression.getValue());
		
	}

	@Override
	public void visitTurtleRepeatExpression(
			TurtleRepeatExpression repeatExpression) {
		int times = repeatExpression.getTimesToRepeat(context);		
		repeatExpression.acceptOnCommandList(times, this);
		
	}

	/**
	 * return the distanceMoved
	 */
	public int getDistanceMoved() {
		return distanceMoved;
	}

}
