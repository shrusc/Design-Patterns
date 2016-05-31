package com.java.turtlepattern;


/*
 * Visits the interpreter objects and creates a list of command objects.
 */
public class TurtleInterpreterVisitor extends TurtleVisitor {
	private TurtleList<TurtleCommand> commandList = new TurtleList<TurtleCommand>();	
	
	public TurtleInterpreterVisitor(TurtleContext context){
		this.context = context;
	}
	
	@Override
	public void visitTurtleMoveExpression(TurtleMoveExpression moveExpression) {
		commandList.add(new TurtleMoveCommand(context, moveExpression));
	}
	
	@Override
	public void visitTurtleTurnExpression(TurtleTurnExpression turnExpression) {
		commandList.add(new TurtleTurnCommand(context, turnExpression));
	}

	@Override
	public void visitTurtlePenUpExpression(TurtlePenUpExpression penUpExpression) {
		commandList.add(new TurtlePenUpCommand(context));
	}

	@Override
	public void visitTurtlePenDownExpression(
			TurtlePenDownExpression penDownExpression) {
		commandList.add(new TurtlePenDownCommand(context));
	}
	
	@Override
	public void visitTurtleAssignExpression(
			TurtleAssignExpression assignExpression) {
		commandList.add(new TurtleAssignCommand(assignExpression.getVariableName(), assignExpression.getValue(), context));
	}
	
	@Override
	public void visitTurtleRepeatExpression(TurtleRepeatExpression repeatExpression){		
		commandList.add(new TurtleRepeatCommand(repeatExpression,context));
	}

	/**
	 * return the commandList
	 */
	public TurtleList<TurtleCommand> getCommandList() {
		return commandList;
	}

}
