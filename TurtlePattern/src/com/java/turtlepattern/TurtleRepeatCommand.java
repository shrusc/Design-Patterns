package com.java.turtlepattern;

/*
 * Command class to create a list of command objects
 * of statements in repeat block.
 */
public class TurtleRepeatCommand extends TurtleCommand{
	private TurtleRepeatExpression repeatExpression;
	TurtleContext context;

	public TurtleRepeatCommand(TurtleRepeatExpression repeatExpression,
			TurtleContext context) {
		this.repeatExpression = repeatExpression;
		this.context = context;
	}

	@Override
	public void execute() {
		
	}

	@Override
	public void undo() {
		
	}
	
	/*
	 * Invokes the visitor on interpreter objects in the list
	 * to obtain command list
	 */
	public TurtleList<TurtleCommand> getCommandList(){
		int timesToRepeat = repeatExpression.getTimesToRepeat(context);
		TurtleInterpreterVisitor visitor = new TurtleInterpreterVisitor(context);
		
		repeatExpression.acceptOnCommandList(timesToRepeat, visitor);		
		TurtleList<TurtleCommand> commandList = visitor.getCommandList();
		
		return commandList;
	}

}
