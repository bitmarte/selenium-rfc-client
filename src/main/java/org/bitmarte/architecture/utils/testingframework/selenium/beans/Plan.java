package org.bitmarte.architecture.utils.testingframework.selenium.beans;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author bitmarte
 *
 */
@XStreamAlias("plan")
public class Plan {

	@XStreamImplicit
	private List<Run> runs;

	public List<Run> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}
}
