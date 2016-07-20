package org.bitmarte.architecture.utils.testingframework.selenium.beans.plan;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.reports.PlanReport;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.Run;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_TestResult;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("plan")
public class Plan {

	@XStreamAlias("executions")
	@XStreamAsAttribute
	private int executions;

	@XStreamOmitField
	private String planName;

	@XStreamOmitField
	private PlanReport planReport;

	@XStreamImplicit
	private List<Run> runs;

	public int getExecutions() {
		return executions;
	}

	public void setExecutions(int executions) {
		this.executions = executions;
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
