package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.PlanReport;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("plan")
public class Plan {

	private static final Logger LOG = LoggerFactory.getLogger(Plan.class);

	@XStreamAlias("concurrentExecutors")
	@XStreamAsAttribute
	private int concurrentExecutors;

	@XStreamOmitField
	private String planName;

	@XStreamOmitField
	private PlanReport planReport;

	@XStreamAlias("cookiesRemoveAll")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "true", "false" })
	@XStreamAsAttribute
	private boolean cookiesRemoveAll;

	@XStreamAlias("cookiesRemove")
	@XStreamAsAttribute
	private String cookiesRemove;

	@XStreamAlias("fullscreen")
	@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "true", "false" })
	@XStreamAsAttribute
	private boolean fullscreen;

	@XStreamImplicit
	private List<Run> runs;

	public int getConcurrentExecutors() {
		return concurrentExecutors;
	}

	public void setConcurrentExecutors(int concurrentExecutors) {
		this.concurrentExecutors = concurrentExecutors;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

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

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public PlanReport getPlanReport() {
		if (planReport == null) {
			this.planReport = new PlanReport();
		}
		return planReport;
	}

	public int getTotalRun() {
		return this.runs.size();
	}

	public int getSuccessRun() {
		int counter = 0;
		for (Run run : this.runs) {
			if (run.getRunReport().getTestResult() != null
					&& run.getRunReport().getTestResult().equals(E_TestResult.SUCCESS.name())) {
				counter++;
			}
		}
		return counter;
	}

	public int getErrorRun() {
		int counter = 0;
		for (Run run : this.runs) {
			if (run.getRunReport().getTestResult() != null
					&& run.getRunReport().getTestResult().equals(E_TestResult.ERROR.name())) {
				counter++;
			}
		}
		return counter;
	}

	public int getUntestedRun() {
		int counter = 0;
		for (Run run : this.runs) {
			if (run.getRunReport().getTestResult() == null) {
				counter++;
			}
		}
		return counter;
	}

}
