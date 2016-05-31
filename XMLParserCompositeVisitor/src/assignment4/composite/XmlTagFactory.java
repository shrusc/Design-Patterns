package assignment4.composite;

import java.util.Stack;

/**
 * Factory Class which returns the appropriate tag object
 * and perform additional handling
 */
public class XmlTagFactory {

	//Stack holding the composite tag objects
	private Stack<XmlTag> composites;

	public XmlTagFactory(){
		composites = new Stack<XmlTag>();
	}

	/**
	 * method to get object for type of tag
	 */
	public XmlTag getXmlTagObject(String tagName) {	
		XmlTag newTag = null;
		if(tagName.compareTo("CS635Document") == 0){
			newTag = new XmlCompositeTag("CS635Document");
			//add the current tag as the child to the enclosing composite tag
			if(!composites.isEmpty()) 
				composites.peek().addChildTag(newTag);
			//push the composite tag onto the stack
			composites.push(newTag);
		}
		else if(tagName.compareTo("header") == 0) {
			newTag = new XmlLeafTag("header");
			//add the current tag as the child to the enclosing composite tag
			composites.peek().addChildTag(newTag);
		}	 
		else if(tagName.compareTo("text") == 0){
			newTag = new XmlLeafTag("text");
			//add the current tag as the child to the enclosing composite tag
			composites.peek().addChildTag(newTag);
		}
		return newTag;
	}

	/**
	 * method to handle the tag end.
	 */
	public void removeObject(String tagName) {
		//remove the composite tag from the stack
		if(tagName.compareTo("CS635Document") == 0)
			composites.pop();
	}

}
