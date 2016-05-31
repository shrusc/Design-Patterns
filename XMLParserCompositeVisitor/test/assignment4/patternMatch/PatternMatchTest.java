package assignment4.patternMatch;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import assignment4.patternMatch.Match;

public class PatternMatchTest {
	private Match patternMatch;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method to test the start Index of various patterns 
	 * in the target string  
	 */
	@Test
	public void test() {
		//Test 1 - with wild character .
		patternMatch = new Match("c.t");
		assertEquals(4,patternMatch.findFirstIn("cacacat"));
		//Test 2 - with wild character *
		patternMatch = new Match("c*t");
		assertEquals(0,patternMatch.findFirstIn("cacacat"));
		//Test 3 - pattern not matching 
		patternMatch = new Match("caat");
		assertEquals(-1,patternMatch.findFirstIn("cacacat"));
		//Test 4 - with wild characters . and *
		patternMatch = new Match("g*e.n");
		assertEquals(4,patternMatch.findFirstIn("designpatterns"));
	}
}
