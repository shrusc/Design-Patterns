package assignment4.composite;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Class to test the Visitor on the XML Composite tree  
 */
public class XmlCompositeVisitorTest {

	private SAXXmlParser handler;
	private String[] htmlPrint;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		htmlPrint = new String[4];
		htmlPrint[0] = "This is an example";
		htmlPrint[1] = "Not much here";
		htmlPrint[2] = "Another header";
		htmlPrint[3] = "Just text here";

		handler = new SAXXmlParser();
		// Use the default (non-validating) parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse( new File("sample.xml"), handler );
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method to test the Visitor on XML to print only
	 * the header info  
	 */
	@Test
	public void testPrintHeaderVisitor() {
		PrintHeaderVisitor printHeader = new PrintHeaderVisitor();
		handler.root().accept(printHeader);
		//Splitting all the lines on the newline character 
		String[] headerLines = printHeader.getHeaderContent().split("\\r\\n|\\n|\\r");
		assertEquals(headerLines[0],htmlPrint[0]);
		assertEquals(headerLines[1],htmlPrint[2]);

	}

	/**
	 * Test method to test the Visitor on XML to print 
	 * the HTML info of an XML
	 */
	@Test
	public void testPrintHtmlVisitor(){
		PrintHtmlVisitor printHtml = new PrintHtmlVisitor();
		handler.root().accept(printHtml);
		//Splitting all the lines on the newline character 
		String[] htmlLines = printHtml.getHtmlContent().split("\\r\\n|\\n|\\r"); 
		for(int i=0; i< htmlLines.length; i++) {
			assertEquals(htmlLines[i],htmlPrint[i]);
		}
	}

}
