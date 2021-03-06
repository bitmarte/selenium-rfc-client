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
 * This is the plan, serialzed from configuration
 * 
 * @author bitmarte
 */
@XStreamAlias("plan")
public class Plan {

	/**
	 * Indicates the number of concurrent run (of plan) that will be execute at
	 * the same time
	 */
	@XStreamAlias("executions")
	@XStreamAsAttribute
	private int executions;

	/**
	 * The name of the plan, unique
	 */
	@XStreamOmitField
	private String planName;

	/**
	 * @see PlanReport
	 */
	@XStreamOmitField
	private PlanReport planReport;

	/**
	 * The list of {@link Run} in the plan
	 */
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

	public String getPlanResult() {
		String result = E_TestResult.SUCCESS.name();
		for (Run run : this.runs) {
			if (run.getRunReport().getTestResult() != null) {
				if (run.getRunReport().getTestResult().equals(E_TestResult.TIMEOUT.name())) {
					result = E_TestResult.TIMEOUT.name();
				}
				if (run.getRunReport().getTestResult().equals(E_TestResult.ERROR.name())) {
					result = E_TestResult.ERROR.name();
				}
			} else {
				// There was an error because a run does not have TestResult
				result = E_TestResult.ERROR.name();
			}
		}
		return result;
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
