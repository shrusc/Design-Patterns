package assignment3;

import org.json.JSONException;

/**
 * Class which executes and undoes the remove operation on the database 
 */
public class DatabaseRemoveCommand extends DatabaseCommand{

	private String key;
	private Object data;

	public DatabaseRemoveCommand(String key) {
		this.key = key;
	}

	/**
	 * Removes the data at the key from the given database
	 */
	@Override
	public Object execute(NoSqlDatabase database) {
		try {
			data = database.get(key);
			database.remove(key);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns a new put command to undo the remove operation on the database
	 * @returns - the put command. 
	 */
	@Override
	public DatabaseCommand undo(NoSqlDatabase database) {
		return new DatabasePutCommand(key, data);
	}

}
