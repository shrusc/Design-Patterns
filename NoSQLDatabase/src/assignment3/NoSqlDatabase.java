package assignment3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

/**
 * Class which has the NoSql Database and the operations made on it.
 */
public class NoSqlDatabase extends Observable implements Observer{

	private JSONObject database;
	private  Memento memento;
	private File commandFile;

	public NoSqlDatabase() throws JSONException {
		database = new JSONObject();
		memento = new Memento();
		commandFile = new File("commands.txt");
	}
	
	/**
	 * Puts the data to the database. 
	 * @returns - this database instance
	 * @throws InvalidDatabaseDataException - if the data type is not one of the allowed type
	 */
	public NoSqlDatabase put(String key, Object data) throws JSONException,InvalidDatabaseDataException{
		if(data instanceof Integer || 
				data instanceof String ||
				data instanceof Double ||
				data instanceof DatabaseArray ||
				data instanceof DatabaseObject ||
				data == null){
			if(data instanceof DatabaseArray)
				database.put(key,new JSONArray(data.toString()));
			else if (data instanceof DatabaseObject)
				database.put(key,new JSONObject(data.toString()));
			else
				database.put(key,data);
		}
		else 
			throw new InvalidDatabaseDataException();
		JSONObject object = new JSONObject();
		object.put(key, data);
		String content = "Put "+ object.toString();
		writeToCommandFile(content);
		setChanged();
		notifyObservers(key);
		return this;
	}
	
	/**
	 * Gets the data from the database, at the given key. 
	 * @returns - this data
	 */
	public Object get(String key) throws JSONException {
		Object data = null;
		data = database.get(key);
		return data;
	}

	public Integer getInt(String key) throws JSONException {
		return new Integer(get(key).toString());
	}

	public Double getDouble(String key) throws JSONException {
		return new Double(get(key).toString());
	}

	public String getString(String key) throws JSONException {
		return get(key).toString();
	}

	public DatabaseObject getDatabaseObject(String key) throws JSONException {
		return DatabaseObject.fromString(get(key).toString());
	}

	public DatabaseArray getDatabaseArray(String key) throws JSONException {
		return DatabaseArray.fromString(get(key).toString());
	}
	
	/**
	 * Removes the data from the database at the given key.
	 */
	public void remove(String key) throws JSONException {
		Object data = null;
		data = database.get(key);
		if(data != null) {
			database.remove(key);
			JSONObject object = new JSONObject();
			object.put(key, data);
			String content = "Remove "+ object.toString();
			writeToCommandFile(content);
			setChanged();
			notifyObservers(key);
		}
	}
	
	/**
	 * Replaces the data in the database at the given key
	 */
	public void replace(String key, Object data) throws JSONException {
		Object originalData = null;
		originalData = database.get(key);
		if(originalData != null) {
			database.remove(key);
			database.put(key, data);
			JSONObject object = new JSONObject();
			object.put(key, data);
			String content = "Replace "+ object.toString();
			writeToCommandFile(content);
			setChanged();
			notifyObservers(key);
		}
	}

	/**
	 * Returns the cursor to the provided key in the database. 
	 * @returns - the cursor
	 */
	public Cursor getCursor (String key) throws JSONException {
		if(get(key) != null) {
			Cursor newCursor = new Cursor (key,get(key));
			this.addObserver(newCursor);
			return newCursor;
		}
		return null;
	}
	
	/**
	 * Updates the database value if the value changes in the cursor for
	 * the given key passed as the argument.
	 */
	@Override
	public void update(Observable observable, Object arg) {
		if(observable instanceof Cursor){
			if(database.has(arg.toString())) {
				Cursor cursor = (Cursor) observable;
				try {
					replace(arg.toString(),cursor.get());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Returns a transaction to the database. 
	 * @returns - the transaction instance
	 */
	public Transaction transaction() throws JSONException {
		return new Transaction(this);
	}

	/**
	 * Stores the database state in the default snapshot file
	 * and deletes the default command file
	 */
	public void snapshot() throws JSONException {
		snapshot(new File("commands.txt"),new File("dbSnapshot.txt"));
	}

	/**
	 * Stores the database state in the given snapshot file 
	 * and deletes the given command file
	 */
	public void snapshot(File commands, File snapshot) throws JSONException {
		memento = new Memento(snapshot);
		memento.storeMemento(database.toString());
		commands.delete();
	}

	/**
	 * Restores the database state from the default snapshot file 
	 * and applies the commands from the default command file
	 */
	public void recover() throws JSONException {
		recover(new File("commands.txt"),new File("dbSnapshot.txt"));
	}

	/**
	 * Restores the database state from the given snapshot file 
	 * and applies the commands from the given command file
	 */
	public void recover(File commands, File snapshot) throws JSONException {
		memento = new Memento(snapshot);
		if(memento != null) {
			database = new JSONObject(memento.restoreMemento());
		}
		readFromCommandFile(commands);
	}

	/**
	 * Renames the command file 
	 */
	public void renameCommandFile(String name) throws JSONException {
		commandFile = new File(name);
	}

	/**
	 * Writes the command to the command file 
	 */
	private void writeToCommandFile(String content){
		try {
			//if file doesn't exists, then create it
			if(!commandFile.exists()){
				commandFile.createNewFile();
			}
			//true = append file
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(commandFile,true));
			bufferWriter.write(content);
			bufferWriter.newLine();
			bufferWriter.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Reads the commands from the command file and applies it to the database
	 */
	private void readFromCommandFile(File commands){
		try {
			if(commands.exists()) {
				String sCurrentLine;
				BufferedReader br = new BufferedReader(new FileReader(commands));
				while ((sCurrentLine = br.readLine()) != null) {
					String[] splitString = sCurrentLine.split(" ", 2);
					try {
						JSONObject json = new JSONObject(splitString[1]);
						@SuppressWarnings("unchecked")
						Iterator<String> keySet = json.keys();
						switch(splitString[0]) {
						case "Put":
							while (keySet.hasNext()) {
								String key = (String)keySet.next();
								database.put(key, json.get(key));
							}				
							break;

						case "Remove":
							while (keySet.hasNext()) {
								String key = (String)keySet.next();
								database.remove(key);
							}				
							break;

						case "Replace":
							while (keySet.hasNext()) {
								String key = (String)keySet.next();
								Object data = json.get(key);
								database.remove(key);
								database.put(key, data);
							}				
							break;

						default:
							break;
						}
					}catch(JSONException e){
						e.printStackTrace();
					}
				}
				br.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
