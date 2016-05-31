package assignment3;

import java.util.Stack;

/**
 * Class representing the Transaction which can be made on the Database
 */
public class Transaction {

	private NoSqlDatabase database;
	private boolean active;
	private Stack<DatabaseCommand> undoStack;

	public Transaction(NoSqlDatabase database) {
		this.database = database;
		this.active = true;
		this.undoStack = new Stack<DatabaseCommand>();
	}

	/**
	 * Putting data to the database through the transaction 
	 */
	public void put(String key, Object value) throws TransactionFailedException, InvalidDatabaseDataException {
		if(!active) throw new TransactionFailedException ("Transaction already committed");
		DatabasePutCommand newTransaction = new DatabasePutCommand(key,value);
		newTransaction.execute(database);
		//Store it in the undoStack in case there is an abort
		undoStack.add(newTransaction);
	}

	/**
	 * Get the data from the database through the transaction
	 * Don't store the get on the stack since we can't undo it
	 */
	public Object get(String key) throws TransactionFailedException {
		if(!active) throw new TransactionFailedException ("Transaction already committed");
		DatabaseGetCommand newTransaction = new DatabaseGetCommand(key);
		return newTransaction.execute(database);
	}

	public int getInt(String key) throws TransactionFailedException {
		return (int)get(key);
	}

	public String getString(String key) throws TransactionFailedException {
		return (String)get(key);
	}

	public DatabaseObject getDataBaseObject(String key) throws TransactionFailedException {
		return (DatabaseObject)get(key);
	}

	public DatabaseArray getDatabaseArray(String key) throws TransactionFailedException {
		return (DatabaseArray)get(key);
	}

	/**
	 * Removing a database entry via the transaction 
	 */
	public void remove(String key) throws TransactionFailedException {
		if(!active) throw new TransactionFailedException ("Transaction already committed");
		DatabaseRemoveCommand newTransaction = new DatabaseRemoveCommand(key);
		newTransaction.execute(database);
		//Store it in the undoStack in case there is an abort
		undoStack.add(newTransaction);
	}

	/**
	 * Makes the operations of the transaction permanent
	 */
	public void commit(){
		active = false;
	}

	/**
	 * Undoes all the operations of the transaction 
	 */
	public void abort() {
		if(active) {
			for(DatabaseCommand command : undoStack) {
				DatabaseCommand undoCommand = command.undo(database);
				if(undoCommand != null)
					undoCommand.execute(database);
			}
			active = false;
		}
	}

	/**
	 * Returns whether the transaction is active or not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Exception Class used to throw an exception when unable to 
	 * perform the transaction 
	 */
	@SuppressWarnings("serial")
	private class TransactionFailedException extends Exception {
		TransactionFailedException(String s){
			super(s);
		}
	}

}
