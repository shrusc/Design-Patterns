package assignment3;

import org.json.JSONException;

/**
 * Class which executes and undoes the put operation on the database 
 */
public class DatabasePutCommand extends DatabaseCommand {

	private String key;
	private Object data;

	public DatabasePutCommand (String key, Object data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * Puts the data to the given database
	 * @returns - the database on which the put was executed. 
	 */
	@Override
	public Object execute(NoSqlDatabase database) {
		NoSqlDatabase changedDatabase = null;
		try {
			changedDatabase = database.put(key, data);

		} catch (JSONException | InvalidDatabaseDataException e) {
			e.printStackTrace();
		}
		return changedDatabase;
	}


	/**
	 * Returns a new remove command to undo the put operation on the database
	 * @returns - the remove command. 
	 */
	@Override
	public DatabaseCommand undo(NoSqlDatabase database) {
		return new DatabaseRemoveCommand(key);	
	}	

}
