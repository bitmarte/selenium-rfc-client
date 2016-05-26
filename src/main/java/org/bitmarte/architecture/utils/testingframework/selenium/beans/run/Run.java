package org.bitmarte.architecture.utils.testingframework.selenium.beans.run;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.RunReport;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.action.A_BrowserAction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("run")
public class Run {

	@XStreamOmitField
	private RunReport runReport;

	@XStreamAlias("runName")
	private String runName;

	@XStreamAlias("authentication")
	private Authentication authentication;

	@XStreamAlias("successCondition")
	private SuccessCondition successCondition;

	@XStreamAlias("browserActions")
	private List<A_BrowserAction> browserActions;

	@XStreamAlias("errorConditions")
	private List<ErrorCondition> errorConditions;

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public SuccessCondition getSuccessCondition() {
		return successCondition;
	}

	public void setSuccessCondition(SuccessCondition successCondition) {
		this.successCondition = successCondition;
	}

	public List<A_BrowserAction> getBrowserActions() {
		return browserActions;
	}

	public void setBrowserActions(List<A_BrowserAction> browserActions) {
		this.browserActions = browserActions;
	}

	public List<ErrorCondition> getErrorConditions() {
		return errorConditions;
	}

	public void setErrorConditions(List<ErrorCondition> errorConditions) {
		this.errorConditions = errorConditions;
	}

	public RunReport getRunReport() {
		if (runReport == null) {
			this.runReport = new RunReport();
		}
		return runReport;
	}

}
