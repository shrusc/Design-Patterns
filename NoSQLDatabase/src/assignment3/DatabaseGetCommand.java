package assignment3;

import org.json.JSONException;

/**
 * Class which executes the get operation on the database 
 */
public class DatabaseGetCommand extends DatabaseCommand {

	private String key;
	private Object data;

	public DatabaseGetCommand(String key)  {
		this.key = key;
	}

	/**
	 * Gets the data for the given key from the database
	 * @returns - the data 
	 */
	@Override
	public Object execute(NoSqlDatabase database) {
		try {
			data = database.get(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * No point in undoing a get 
	 */
	@Override
	public DatabaseCommand undo(NoSqlDatabase database) {
		return null;		
	}

}
