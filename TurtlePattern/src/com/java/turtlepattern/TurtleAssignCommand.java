package com.java.turtlepattern;

/*
 * Command class to execute assign statement.
 */

public class TurtleAssignCommand extends TurtleCommand {
	private int value;
	private String variableName;
	private TurtleContext context;
	
	public TurtleAssignCommand(String variableName, int value,TurtleContext context) {
		this.value = value;
		this.variableName = variableName;
		this.context =  context;
	}
	
	/*
	 * On executing the command, it stores the value of the variable in context.
	 */
	@Override
	public void execute() {
		context.setValue(variableName, value);
	}

	/*
	 * Removes the value from context.
	 */
	@Override
	public void undo() {
		context.removeValue(variableName);
	}

}
