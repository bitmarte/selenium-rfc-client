package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Simulates the mouse scrolling
 * 
 * @author bitmarte
 */
@XStreamAlias("scroll")
public class ScrollAction extends A_BrowserAction {

	/**
	 * Indicates the element that will be select, in order to scroll into a
	 * specific region of a page
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
	 * Vertical scroll
	 */
	@XStreamAlias("topPx")
	private int topPx;

	/**
	 * Horizontal scroll
	 */
	@XStreamAlias("leftPx")
	private int leftPx;

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

	public int getTopPx() {
		return topPx;
	}

	public void setTopPx(int topPx) {
		this.topPx = topPx;
	}

	public int getLeftPx() {
		return leftPx;
	}

	public void setLeftPx(int leftPx) {
		this.leftPx = leftPx;
	}

}
