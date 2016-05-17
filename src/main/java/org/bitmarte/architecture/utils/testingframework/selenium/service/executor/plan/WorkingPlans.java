package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan;

import java.util.ArrayList;
import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class WorkingPlans {

	private static final Logger LOG = LoggerFactory.getLogger(WorkingPlans.class);

	private List<Plan> workingPlans = new ArrayList<Plan>();
	private int workedPlans = 0;

	public void pushWorkingPlan(Plan plan) {
		LOG.info("adding plan '" + plan.getPlanName() + "' to workingPlans...");
		this.workingPlans.add(plan);
	}

	public List<Plan> getPlans() {
		return this.workingPlans;
	}

	public synchronized void regWorkedPlan(Plan p) {
		LOG.debug(p.getPlanName() + " completed!");
		this.workedPlans++;
	}

	public boolean isFinish() {
		boolean isFinish = false;
		if (this.workedPlans == this.workingPlans.size()) {
			isFinish = true;
		}
		return isFinish;
	}
}
