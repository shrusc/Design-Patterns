package com.java.turtlepattern;

/*
 * Abstract class defining the functionalities of turtle visitor
 */
public abstract class TurtleVisitor {
	protected TurtleContext context;
	
	public abstract void visitTurtleMoveExpression(TurtleMoveExpression moveExpression);
	public abstract void visitTurtleTurnExpression(TurtleTurnExpression turnExpression);
	public abstract void visitTurtlePenUpExpression(TurtlePenUpExpression penUpExpression);
	public abstract void visitTurtlePenDownExpression(TurtlePenDownExpression penDownExpression);
	public abstract void visitTurtleAssignExpression(TurtleAssignExpression assignExpression);
	abstract void visitTurtleRepeatExpression(TurtleRepeatExpression repeatExpression);	
}
