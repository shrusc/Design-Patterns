package assignment3;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseArrayTest {
	private DatabaseObject customerA = null;
	private DatabaseArray testArray = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		customerA = DatabaseObject.fromString("{\"name\": \"Bill Watson\", \"account\": 12343, "
				+ "\"balance\": 5000.23}");
		testArray = DatabaseArray.fromString("[1, \"checking\", 3.456, \"savings\", \"money market\"]");
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method to test the DatabaseArray put,remove,toString 
	 * and length methods  
	 */
	@Test
	public void tests() {
		try {
			testArray.put(customerA);
			Thread.sleep(50);
			assertEquals(customerA.toString(),testArray.get(5).toString());

			String nullString = null;
			testArray.put(nullString);
		}catch (Exception e){
			assertEquals("Invalid Database Data Format", e.getMessage());
		}
		try{
			int[] anArray = new int[3];
			anArray[0] = 100;
			anArray[1] = 200;
			anArray[2] = 300;
			testArray.put(anArray);
		} catch (Exception e) {
			assertEquals("Invalid Database Data Format", e.getMessage());
		}

		Object data;
		data =  testArray.remove(5);
		assertEquals(customerA.toString(),data.toString());

		assertNull(testArray.remove(5));

		assertEquals(5,testArray.length());
	}

	/**
	 * Test method for the get methods of DatabaseArray
	 * @throws JSONException 
	 * @throws InvalidDatabaseDataException 
	 */
	@Test
	public void testGetOperations() throws JSONException, InvalidDatabaseDataException {
		assertEquals("checking",testArray.getString(1));
		assertEquals(1,testArray.getInt(0));
		assertEquals(3.456,testArray.getDouble(2),0.01);
		testArray.put(customerA);
		assertEquals(customerA.toString(),testArray.getObject(5).toString());
	}

}
