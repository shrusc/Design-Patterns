package assignment3;

/**
 * Abstract Class representing the DatabaseCommand operations
 */
public abstract class DatabaseCommand {
	public abstract Object execute(NoSqlDatabase database);
	public abstract DatabaseCommand undo(NoSqlDatabase database);	
}
