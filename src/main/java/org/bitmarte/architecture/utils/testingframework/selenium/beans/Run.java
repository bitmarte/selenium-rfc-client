package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("run")
public class Run {

	@XStreamAlias("cookiesRemoveAll")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = {
			"true", "false" })
	@XStreamAsAttribute
	private boolean cookiesRemoveAll;

	@XStreamAlias("cookiesRemove")
	@XStreamAsAttribute
	private String cookiesRemove;

	@XStreamAlias("runName")
	private String runName;

	@XStreamAlias("url")
	private String url;

	@XStreamAlias("clickByXPATH")
	private String clickByXPATH;

	@XStreamAlias("inputFields")
	private List<InputField> inputFields;

	@XStreamAlias("successCondition")
	private SuccessCondition successCondition;

	@XStreamAsAttribute
	@XStreamAlias("windowWidthPx")
	private int windowWidthPx = -1;

	@XStreamAsAttribute
	@XStreamAlias("windowHeightPx")
	private int windowHeightPx = -1;

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClickByXPATH() {
		return clickByXPATH;
	}

	public void setClickByXPATH(String clickByXPATH) {
		this.clickByXPATH = clickByXPATH;
	}

	public List<InputField> getInputFields() {
		return inputFields;
	}

	public void setInputFields(List<InputField> inputFields) {
		this.inputFields = inputFields;
	}

	public SuccessCondition getSuccessCondition() {
		return successCondition;
	}

	public void setSuccessCondition(SuccessCondition successCondition) {
		this.successCondition = successCondition;
	}

	public int getWindowWidthPx() {
		return windowWidthPx;
	}

	public void setWindowWidthPx(int windowWidthPx) {
		this.windowWidthPx = windowWidthPx;
	}

	public int getWindowHeightPx() {
		return windowHeightPx;
	}

	public void setWindowHeightPx(int windowHeightPx) {
		this.windowHeightPx = windowHeightPx;
	}

	public String getCookiesRemove() {
		return cookiesRemove;
	}

	public void setCookiesRemove(String cookiesRemove) {
		this.cookiesRemove = cookiesRemove;
	}

	public boolean isCookiesRemoveAll() {
		return cookiesRemoveAll;
	}

	public void setCookiesRemoveAll(boolean cookiesRemoveAll) {
		this.cookiesRemoveAll = cookiesRemoveAll;
	}

}
