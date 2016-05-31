package assignment3;

import static org.junit.Assert.*;
import java.io.File;
import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NoSqlDatabaseTest {

	private NoSqlDatabase database;
	private DatabaseObject customerA = null;
	private DatabaseObject customerB = null;
	private DatabaseObject customerC = null;
	private DatabaseArray accountTypes = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		database = new NoSqlDatabase();
		try {
			customerA = DatabaseObject.fromString("{\"name\": \"Bill Watson\", \"account\": 12343, "
					+ "\"balance\": 5000}");
			customerB = DatabaseObject.fromString("{\"name\": \"Ross Geller\", \"account\": 12345, "
					+ "\"balance\": 15000}");
			customerC = DatabaseObject.fromString("{\"name\": \"Jason Smith\", \"account\": 12347, "
					+ "\"balance\": 2000}");
			accountTypes = DatabaseArray.fromString("[\"checking\" , \"savings\" , \"money market\"]");
			DatabasePutCommand putCustomerA = new DatabasePutCommand("Customer A", customerA);
			DatabasePutCommand putCustomerB = new DatabasePutCommand("Customer B", customerB);
			DatabasePutCommand putCustomerC = new DatabasePutCommand("Customer C", customerC);
			DatabasePutCommand putAccountTypes = new DatabasePutCommand("Account Types", accountTypes);
			putCustomerA.execute(database);
			putCustomerB.execute(database);
			putCustomerC.execute(database);
			putAccountTypes.execute(database);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method to test the Database command classes put,remove,
	 * Get and replace  
	 */
	@Test
	public void testCommands() throws InvalidDatabaseDataException {
		
		//Test 1- Test the Put and Get Commands
		DatabasePutCommand put = new DatabasePutCommand("id", 0001);
		put.execute(database);
		DatabaseGetCommand get = new DatabaseGetCommand("id");
		assertEquals(0001,get.execute(database));
		
		//Test 2- Test the Replace Command
		DatabaseReplaceCommand replace = new DatabaseReplaceCommand("id",0002);
		replace.execute(database);
		assertEquals(0002,get.execute(database));
		
		//Test 3- Test the Remove Command
		DatabaseRemoveCommand remove = new DatabaseRemoveCommand("id");
		remove.execute(database);
		try{
			get.execute(database);
		}catch(Exception e) {
			assertEquals("JSONObject[\"id\"] not found.", e.getMessage());
		}
		
		//Test 4- Test the InvalidDatabaseDataException
		int[] anArray;
        anArray = new int[3];
        anArray[0] = 100;
        anArray[1] = 200;
        anArray[2] = 300;
        try{
        	DatabasePutCommand putJavaArray = new DatabasePutCommand("Java Array", anArray);
        	putJavaArray.execute(database);
        }catch(Exception e){
        	assertEquals("Invalid Database Data Format", e.getMessage());
        }

	}

	/**
	 * Test method to test the Transactions on the database  
	 */
	@Test
	public void testTransactions() throws InvalidDatabaseDataException {
		
		//Test 1 - Transaction to test the commit functionality
		Transaction transaction1 = null;
		try {
			transaction1 = database.transaction();
			DatabaseObject balanceInfo = (DatabaseObject) transaction1.get("Customer B");
			transaction1.remove("Customer B");
			if (balanceInfo.getInt("balance") < 3000)
				transaction1.abort();
			else
				transaction1.commit();
		} catch (Exception e) {
			transaction1.abort();
		}
		try{
			database.getDatabaseObject("Customer B");
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals("JSONObject[\"Customer B\"] not found.", e.getMessage());
		}

		//Test 2 - Transaction to test the abort functionality
		Transaction transaction2 = null;
		try {
			transaction2 = database.transaction();
			DatabaseObject balanceInfo = (DatabaseObject) transaction2.get("Customer C");
			transaction2.remove("Customer C");
			if (balanceInfo.getInt("balance") < 3000)
				transaction2.abort();
			else
				transaction2.commit();
		} catch (Exception e) {
			transaction2.abort();
		}
		try {
			assertEquals(customerC.toString(),database.getDatabaseObject("Customer C").toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		//Test 3 - Test if the isActive functionality and the TransactionFailedException
		Transaction transaction3 = null;
		try {
			transaction3 = database.transaction();
			transaction3.put("Customer B",customerB);
			assertTrue(transaction3.isActive());
			transaction3.commit();
			assertFalse(transaction3.isActive());
			transaction3.remove("Customer B");
		} catch (Exception e) {
			assertEquals("Transaction already committed", e.getMessage());
		}
	}

	/**
	 * Test method to test the Cursors to the database
	 */
	@Test
	public void testCursors() throws JSONException, InvalidDatabaseDataException  {
		
		//Test 1 - Cursor data changed, database observing the cursor get the update
		Cursor customerACursor = database.getCursor("Customer A");
		DatabaseObject cursorData = null;
		cursorData = customerACursor.getObject();
		cursorData =  cursorData.put("age", 35);
		assertEquals(customerACursor.getObject().toString(),
				database.getDatabaseObject("Customer A").toString());

		//Test 2 - Database data changed, cursor gets the update too
		Cursor cursor2  = database.getCursor("Customer B");
		DatabaseObject dbObject = database.getDatabaseObject("Customer B");
		dbObject = dbObject.put("Age", 56);
		database.replace("Customer B", dbObject);
		assertEquals(cursor2.getObject().toString(),
				database.getDatabaseObject("Customer B").toString());
	}
	
	/**
	 * Test method to test the snapshot and recover functionalities of the database
	 */
	@Test
	public void testSnapshotAndRecover() throws JSONException, InvalidDatabaseDataException {
		
		//Test 1 - test the default snapshot and recover functions
		database.snapshot();
		NoSqlDatabase defaultRestoredDatabase = new NoSqlDatabase();
		defaultRestoredDatabase.recover();
		assertEquals(accountTypes.toString(),
				defaultRestoredDatabase.getDatabaseArray("Account Types").toString());
		
		//Test 2 - test snapshot and recover from the user defined file.
		defaultRestoredDatabase.renameCommandFile("restoredDbCommands.txt");
		File commandFile = new File("restoredDbCommands.txt");
		File snapshotFile = new File("restoredDbSnapshot.txt");
		defaultRestoredDatabase.snapshot(commandFile, snapshotFile);
		DatabasePutCommand putCustomerC = new DatabasePutCommand("Customer C", customerC);
		putCustomerC.execute(defaultRestoredDatabase);
		
		NoSqlDatabase fileRestoredDatabase = new NoSqlDatabase();
		fileRestoredDatabase.recover(commandFile , snapshotFile);
		//Test to see if data from the restoredDbSnapshot is present
		assertEquals(accountTypes.toString(),
				fileRestoredDatabase.getDatabaseArray("Account Types").toString());
		//Test to see database has applied the commands from restoredDbCommands
		assertEquals(customerC.toString(),
				fileRestoredDatabase.getDatabaseObject("Customer C").toString());
		
	}

}
