package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * The generic test condition, abstract one
 * 
 * @author bitmarte
 */
public abstract class A_TestCondition {

	/**
	 * Indicates the element that will be test, the presence
	 */
	@XStreamAlias("element")
	private String element;

	/**
	 * The content {@link String} that will be compared
	 */
	@XStreamAlias("elementContent")
	private String elementContent;

	/**
	 * The comparator will be used to compare content, @see E_ContentEvaluator
	 */
	@XStreamAlias("contentEvaluator")
	@XStreamAsAttribute
	private String contentEvaluator;

	/**
	 * The extractor
	 */
	@XStreamAlias("elementExtractor")
	@XStreamAsAttribute
	private String elementExtractor;

	/**
	 * Waiting timeout for element extractor, before hits
	 */
	@XStreamAlias("maxTimeOutPerElementExtratorInSec")
	@XStreamAsAttribute
	private long maxTimeOutPerElementExtratorInSec;

	/**
	 * The file name of the screenshot
	 */
	@XStreamAlias("screenshotFileName")
	@XStreamAsAttribute
	private String screenshotFileName;

	/**
	 * Waiting timeout before taking a screenshot
	 */
	@XStreamAlias("waitBeforeScreenshotInMilliSec")
	@XStreamAsAttribute
	private long waitBeforeScreenshotInMilliSec;

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getElementContent() {
		return elementContent;
	}

	public void setElementContent(String elementContent) {
		this.elementContent = elementContent;
	}

	public String getContentEvaluator() {
		return contentEvaluator;
	}

	public void setContentEvaluator(String contentEvaluator) {
		this.contentEvaluator = contentEvaluator;
	}

	public String getElementExtractor() {
		return elementExtractor;
	}

	public void setElementExtractor(String elementExtractor) {
		this.elementExtractor = elementExtractor;
	}

	public long getMaxTimeOutPerElementExtratorInSec() {
		return maxTimeOutPerElementExtratorInSec;
	}

	public void setMaxTimeOutPerElementExtratorInSec(long maxTimeOutPerElementExtratorInSec) {
		this.maxTimeOutPerElementExtratorInSec = maxTimeOutPerElementExtratorInSec;
	}

	public String getScreenshotFileName() {
		return screenshotFileName;
	}

	public void setScreenshotFileName(String screenshotFileName) {
		this.screenshotFileName = screenshotFileName;
	}

	public long getWaitBeforeScreenshotInMilliSec() {
		return waitBeforeScreenshotInMilliSec;
	}

	public void setWaitBeforeScreenshotInMilliSec(long waitBeforeScreenshotInMilliSec) {
		this.waitBeforeScreenshotInMilliSec = waitBeforeScreenshotInMilliSec;
	}

}
