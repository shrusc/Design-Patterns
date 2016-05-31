
package assignment3;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseObjectTest {
	private DatabaseObject customerA = null;
	private DatabaseArray accountTypes = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try {
			customerA = DatabaseObject.fromString("{\"name\": \"Bill Watson\", \"account\": 12343, "
					+ "\"balance\": 5000.23}");
			accountTypes = DatabaseArray.fromString("[\"checking\", \"savings\", \"money market\"]");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method to test the DatabaseObject put,remove,toString 
	 * and length methods  
	 */
	@Test
	public void tests() {
		try {
			customerA =  customerA.put("age", 35);
			DatabaseObject compare = DatabaseObject.fromString("{\"name\": \"Bill Watson\","
					+ "\"account\": 12343, \"balance\": 5000.23 ,\"age\": 35}");
			assertEquals(compare.toString(),customerA.toString());

			String nullString = null;
			customerA =  customerA.put("null", nullString);
		}catch (Exception e){
			assertEquals("Invalid Database Data Format", e.getMessage());
		}
		try{
			int[] anArray;
			anArray = new int[3];
			anArray[0] = 100;
			anArray[1] = 200;
			anArray[2] = 300;
			customerA =  customerA.put("array", anArray);
		} catch (Exception e) {
			assertEquals("Invalid Database Data Format", e.getMessage());
		}
		Object data;
		data =  customerA.remove("age");
		assertEquals("35",data.toString());

		assertNull(customerA.remove("array"));

		assertEquals(3,customerA.length());
	}

	/**
	 * Test method for the get methods of DatabaseObject
	 * @throws JSONException 
	 * @throws InvalidDatabaseDataException 
	 */
	@Test
	public void testGetOperations() throws JSONException, InvalidDatabaseDataException {
		assertEquals("Bill Watson",customerA.getString("name"));
		assertEquals(12343,customerA.getInt("account"));
		assertEquals(5000.23,customerA.getDouble("balance"),0.01);
		customerA = customerA.put("account types", accountTypes);
		assertEquals(accountTypes.toString(),customerA.getArray("account types").toString());
	}


}
