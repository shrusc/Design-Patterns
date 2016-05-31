package assignment3;

import org.json.JSONException;

/**
 * Class which executes and undoes the replace operation on the database 
 */
public class DatabaseReplaceCommand extends DatabaseCommand{

	private String key;
	private Object data;
	private Object originalData;

	public DatabaseReplaceCommand(String key, Object data) {
		this.key = key;
		this.data =  data;
	}

	/**
	 * Replaces the data at the key with the new data 
	 * from the given database
	 */
	@Override
	public Object execute(NoSqlDatabase database) {
		try {
			originalData = database.get(key);
			database.replace(key, data);		
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns a new replace command to undo the original
	 * replace operation on the database
	 * @returns - the replace command. 
	 */
	@Override
	public DatabaseCommand undo(NoSqlDatabase database) {
		return new DatabaseReplaceCommand(key, originalData);
	}

}
