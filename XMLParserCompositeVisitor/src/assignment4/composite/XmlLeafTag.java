package assignment4.composite;

/**
 * Class representing the leaf XML tag
 */
public class XmlLeafTag extends XmlTag{

	private String tagName;
	private String tagBody;

	public XmlLeafTag(String tagName){
		this.tagName = tagName;
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	/**
	 * Method to set the body of the leaf object
	 */
	@Override
	public void setTagBody(String tagBody){
		this.tagBody = tagBody;
	}

	/**
	 * Method to return the body of the leaf object
	 */
	public String getTagBody(){
		return tagBody;
	}

	/**
	 * Method to accept a visitor to the leaf object
	 */
	@Override
	public void accept(XmlTagVisitor visitor) {
		visitor.visitXmlTag(this);	
	}

}