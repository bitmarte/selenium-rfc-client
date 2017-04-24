package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This class allows you to fill the radios
 * 
 * @author bitmarte
 */
@XStreamAlias("radioFill")
public class RadioFillAction extends A_BrowserAction {

	/**
	 * Indicates the radio in page
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
	 * The value that will be select, radio
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
