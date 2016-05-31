package assignment4.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the composite XML tag
 */
public class XmlCompositeTag extends XmlTag {

	private String tagName;
	//List of all the tags inside the composite tag
	private List<XmlTag>childrenTag;

	public XmlCompositeTag(String tagName){
		this.tagName = tagName;
		this.childrenTag = new ArrayList<>();
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	/**
	 * Method to add a child tag object to this composite object
	 */
	@Override
	protected void addChildTag(XmlTag htmlTag){
		childrenTag.add(htmlTag);
	}

	/**
	 * Method to get all the child objects of this composite object
	 */
	@Override
	protected List<XmlTag>getChildren(){
		return childrenTag;
	}

	/**
	 * Method to accept a visitor to the composite object
	 */
	@Override
	public void accept(XmlTagVisitor visitor) {
		visitor.visitXmlTag(this);
	}

}