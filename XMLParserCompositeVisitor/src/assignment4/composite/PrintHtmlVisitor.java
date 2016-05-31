package assignment4.composite;

/**
 * Visitor Class to print the HTML contents of the XML
 */
public class PrintHtmlVisitor extends XmlTagVisitor {

	private StringBuilder xmlContents;

	public PrintHtmlVisitor() {
		xmlContents = new StringBuilder();
	}

	@Override
	public void visitXmlTag(XmlCompositeTag tag) {
		//Visit all the child Tags
		if(!tag.getChildren().isEmpty()) {
			for(XmlTag child : tag.getChildren()) {
				child.accept(this);
			}	
		}
	}

	@Override
	public void visitXmlTag(XmlLeafTag tag) {
		xmlContents.append(tag.getTagBody() + "\n");
	}

	/**
	 * Method to return the HTML contents of the XML
	 */
	public String getHtmlContent(){
		return xmlContents.toString();
	}

}
