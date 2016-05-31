package assignment3;

import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class representing the Cursor to the Database
 */
public class Cursor extends Observable implements Observer {

	private String key;
	private Object value = null;

	public Cursor(String key, Object data) {
		this.key = key;
		this.value = data;
	}
	
	/**
	 * Updates the cursor value if the value changes in the database
	 */
	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof NoSqlDatabase) {
			if(key.compareTo(arg.toString()) == 0) {
				NoSqlDatabase database = (NoSqlDatabase) observable;
				try {
					set(database.get(key));
				} catch (JSONException | InvalidDatabaseDataException e) {
					this.value = null;
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @return the cursor value
	 */
	public Object get() {
		return value;
	}

	public Integer getInt() throws InvalidDatabaseDataException {
		Integer returnValue = new Integer(value.toString());
		if(!(returnValue instanceof Integer)) 
			throw new InvalidDatabaseDataException("Data is not an integer");
		else 
			return returnValue;
	}

	public Double getDouble(String key) throws InvalidDatabaseDataException {
		Double returnValue = new Double(value.toString());
		if(!(returnValue instanceof Double)) 
			throw new InvalidDatabaseDataException("Data is not an integer");
		else 
			return returnValue;
	}

	public String getString() throws InvalidDatabaseDataException {
		if(!(value instanceof String)) 
			throw new InvalidDatabaseDataException("Data is not a string");
		else 
			return (String) value;
	}

	public DatabaseArray getArray() throws JSONException {
		return DatabaseArray.fromString(value.toString());
	}

	public DatabaseObject getObject() throws JSONException  { 
		return DatabaseObject.fromString(value.toString());
	}

	/**
	 * @param data- the value use to set the cursor value to 
	 */
	public void set(Object data) throws JSONException, InvalidDatabaseDataException {
		if(data instanceof Integer || 
				data instanceof String ||
				data instanceof Double ||
				data instanceof DatabaseArray ||
				data instanceof DatabaseObject ||
				data == null) {
			if(data instanceof DatabaseArray)
				this.value = new JSONArray(data.toString());
			else if (data instanceof DatabaseObject)
				this.value = new JSONObject(data.toString());
			else
				this.value = data;
		}
		else 
			throw new InvalidDatabaseDataException();

		setChanged();
		notifyObservers(key);
	}

}
