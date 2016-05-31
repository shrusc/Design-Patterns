package assignment3;

import java.io.*;
import org.json.JSONException;

/**
 * Memento class which stores and restores the database state
 * from the database snapshot file.
 */
public class Memento {

	private File file;

	public Memento() {
		file = new File("dbSnapshot.txt");
	}

	public Memento (File snapshotFile){
		file = snapshotFile;
	}

	protected void storeMemento(String state) throws JSONException{
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			//true = append file
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file,true));
			bufferWriter.write(state);
			bufferWriter.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}

	protected String restoreMemento() throws JSONException{	 
		try {
			if(file.exists()) {
				String fileContents;
				BufferedReader br = new BufferedReader(new FileReader(file));
				fileContents = br.readLine();
				br.close();
				return fileContents;
			}
		} catch(IOException i) {
			i.printStackTrace();
			return null;
		}
		return null;
	}

}

