package assignment3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class representing the Database Array Data type 
 */
public class DatabaseArray {

	private JSONArray array;

	public DatabaseArray(JSONArray data) {
		this.array = data;	
	}

	public void put(Object data) throws InvalidDatabaseDataException, JSONException {
		if(data instanceof Integer || 
				data instanceof String ||
				data instanceof Double ||
				data instanceof DatabaseArray ||
				data instanceof DatabaseObject ||
				data == null) {
			if(data instanceof DatabaseArray)
				array.put(new JSONArray(data.toString()));
			else if (data instanceof DatabaseObject)
				array.put(new JSONObject(data.toString()));
			else
				array.put(data);
		}
		else 
			throw new InvalidDatabaseDataException();
	}

	public Object get(int index) throws JSONException { 
		if(index < array.length())
			return array.get(index);
		else
			throw new JSONException("Database array index out of bounds");
	}

	public int length() {
		return array.length();
	}

	public String toString() {
		return array.toString();
	}

	public Object remove(int index){
		return array.remove(index);
	}

	public double getDouble(int index) throws JSONException {
		return array.getDouble(index);
	}

	public int getInt(int index) throws JSONException {
		return array.getInt(index);
	}

	public String getString(int index) throws JSONException {
		return array.getString(index);
	}

	public DatabaseArray getArray(int index) throws JSONException {
		return new DatabaseArray(array.getJSONArray(index));
	}

	public DatabaseObject getObject(int index) throws JSONException {
		return new DatabaseObject(array.getJSONObject(index));
	}

	public static DatabaseArray fromString(String newArray) throws JSONException {
		return new DatabaseArray(new JSONArray(newArray));
	}

}
