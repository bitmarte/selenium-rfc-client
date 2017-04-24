package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This action allows you to fill the input fiels
 * 
 * @author bitmarte
 */
@XStreamAlias("inputFill")
public class InputFillAction extends A_BrowserAction {

	/**
	 * Indicates the element that will be select
	 */
	@XStreamAlias("element")
	private String element;

	/**
	 * The extractor
	 */
	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

	/**
	 * The string to fill the input text
	 */
	@XStreamAlias("value")
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
