package assignment4.composite;

import java.util.List;

/**
 * Abstract Class for the XML Tag
 */
public abstract class XmlTag {
	protected abstract String getTagName();
	protected abstract void accept(XmlTagVisitor visitor);

	protected void addChildTag(XmlTag htmlTag){
		throw new UnsupportedOperationException("addChildTag is not supportted for this object");
	}
	protected List<XmlTag>getChildren(){
		throw new UnsupportedOperationException("getChildren is not supportted for this object");
	}
	protected void setTagBody(String tagBody){
		throw new UnsupportedOperationException("setTagBody is not supportted for this object");
	}
}