package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Simulates mouse double click
 * 
 * @author bitmarte
 */
@XStreamAlias("doubleClick")
public class DoubleClickAction extends A_BrowserAction {

	/**
	 * The string that indicates the element will be double clicked
	 */
	@XStreamAlias("element")
	private String element;

	/**
	 * The extractor for the element
	 */
	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getElementExtractor() {
		return elementExtractor;
	}

	public void setElementExtractor(String elementExtractor) {
		this.elementExtractor = elementExtractor;
	}

}
