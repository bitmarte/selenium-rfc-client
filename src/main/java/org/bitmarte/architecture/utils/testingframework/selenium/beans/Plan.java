package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("plan")
public class Plan {

	@XStreamAlias("cookiesRemoveAll")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = {
			"true", "false" })
	@XStreamAsAttribute
	private boolean cookiesRemoveAll;

	@XStreamAlias("cookiesRemove")
	@XStreamAsAttribute
	private String cookiesRemove;

	@XStreamImplicit
	private List<Run> runs;

	public List<Run> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runs) {
		this.runs = runs;
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
