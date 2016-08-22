package org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("scroll")
public class ScrollAction extends A_BrowserAction {

	@XStreamAlias("element")
	private String element;

	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

	@XStreamAlias("topPx")
	private int topPx;

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
