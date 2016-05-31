package assignment4.composite;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Class for parsing the XML using SAX and 
 * constructing the composite tree
 */
public class SAXXmlParser extends DefaultHandler {

	private XmlTag root;
	private XmlTag newTag;
	private XmlTagFactory xmlTagFactory;

	public SAXXmlParser() {
		xmlTagFactory = new XmlTagFactory();
		root = null;
		newTag = null;
	}

	public void startDocument() { 
		return;
	}

	public void endDocument() {
		return;
	}

	/**
	 * Method called during start of a XML tag
	 */
	public void startElement (String uri, String name, String qName, Attributes atts) {
		//Use the factory to get the right object
		if(root == null){
			root = xmlTagFactory.getXmlTagObject(qName);
		}
		else {
			newTag = xmlTagFactory.getXmlTagObject(qName);
		}
	}

	/**
	 * Method called during end of a XML tag
	 */
	public void endElement (String uri, String name, String qName) {
		//call the factory method for handling the tag end
		xmlTagFactory.removeObject(qName);
		newTag = null;
	}

	/**
	 * Method which gives the body of the tag
	 */
	public void characters(char[] ch, int start, int length) {
		if(newTag instanceof XmlLeafTag)
			newTag.setTagBody(new String(ch, start, length));
	}

	/**
	 * Returns the root of the composite tree 
	 */
	public XmlTag root() {
		return root;
	}
}