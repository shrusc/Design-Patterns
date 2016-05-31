package assignment4.composite;

/**
 * Abstract Class for the XML tag visitor
 */
public abstract class XmlTagVisitor {
	public abstract void visitXmlTag(XmlCompositeTag tag);
	public abstract void visitXmlTag(XmlLeafTag tag);

}
