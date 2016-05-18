package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("inputFill")
public class InputFillAction extends A_BrowserAction {

	@XStreamAlias("element")
	private String element;

	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

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
