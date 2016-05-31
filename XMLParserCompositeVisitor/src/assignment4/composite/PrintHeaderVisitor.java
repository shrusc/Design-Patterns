package assignment4.composite;

/**
 * Visitor Class to print the XML header Tag contents
 */
public class PrintHeaderVisitor extends XmlTagVisitor {

	private StringBuilder headerContents;

	public PrintHeaderVisitor() {
		headerContents = new StringBuilder();
	}

	@Override
	public void visitXmlTag(XmlCompositeTag tag) {
		//Visit all the child Tags
		if(! tag.getChildren().isEmpty()) {
			for(XmlTag child : tag.getChildren()) {
				child.accept(this);
			}	
		}
	}

	@Override
	public void visitXmlTag(XmlLeafTag tag) {
		if(tag.getTagName().compareTo("header") == 0)
			headerContents.append(tag.getTagBody() + "\n");
	}

	/**
	 * Method to print the XML header Tag contents
	 */
	public String getHeaderContent(){
		return headerContents.toString();
	}

}
