package assignment3;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class representing the Database Object data type
 */
public class DatabaseObject {

	private JSONObject object;

	public DatabaseObject(JSONObject data) {
		this.object = data;	
	}

	public DatabaseObject put(String key, Object data) throws JSONException,InvalidDatabaseDataException {
		if(data instanceof Integer || 
				data instanceof String ||
				data instanceof Double ||
				data instanceof DatabaseArray ||
				data instanceof DatabaseObject ||
				data == null) {
			if(data instanceof DatabaseArray)
				object.put(key,new JSONArray(data.toString()));
			else if (data instanceof DatabaseObject)
				object.put(key,new JSONObject(data.toString()));
			else
				object.put(key,data);
		}
		else 
			throw new InvalidDatabaseDataException();
		return this;
	}

	public Object get(String key) throws JSONException { 
		return object.get(key);
	}

	public int length() {
		return object.length();
	}

	public String toString() {
		return object.toString();
	}

	public Object remove(String key){
		return object.remove(key);
	}

	public double getDouble(String key) throws JSONException {
		return object.getDouble(key);
	}

	public int getInt(String key) throws JSONException {
		return object.getInt(key);
	}

	public String getString(String key) throws JSONException {
		return object.getString(key);
	}

	public DatabaseArray getArray(String key) throws JSONException {
		return new DatabaseArray(object.getJSONArray(key));
	}

	public DatabaseObject getObject(String key) throws JSONException {
		return new DatabaseObject(object.getJSONObject(key));
	}

	public static DatabaseObject fromString(String newObject) throws JSONException {
		return new DatabaseObject(new JSONObject(newObject));
	}

}
