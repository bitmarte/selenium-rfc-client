package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Simulates the combo select filler
 * 
 * @author bitmarte
 */
@XStreamAlias("comboFill")
public class ComboFillAction extends A_BrowserAction {

	/**
	 * The string that indicates the element will be click
	 */
	@XStreamAlias("element")
	private String element;

	/**
	 * The extractor for the element
	 */
	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

	/**
	 * The value will be select
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
