package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * This action allows you to browse from the iframes
 * 
 * @author bitmarte
 */
@XStreamAlias("iFrameSwitch")
public class IFrameSwitchAction extends A_BrowserAction {

	/**
	 * Indicates the element that contains the iframe
	 */
	@XStreamAlias("element")
	private String element;

	/**
	 * The extractor
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
