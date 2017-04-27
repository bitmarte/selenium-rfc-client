package org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan;

import java.util.ArrayList;
import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This bean rappresents the working plans
 * 
 * @author bitmarte
 */
public class WorkingPlans {

	private static final Logger LOG = LoggerFactory.getLogger(WorkingPlans.class);

	private List<Plan> workingPlans = new ArrayList<Plan>();
	private int workedPlans = 0;

	/**
	 * Add all plans to working list, queue
	 * 
	 * @param planList
	 */
	public void addAllPlans(List<Plan> planList) {
		this.workingPlans.addAll(planList);
	}

	public List<Plan> getPlans() {
		return this.workingPlans;
	}

	/**
	 * This method records the end of the test plan. Synchronization for
	 * concurrent running (multi threads execution)
	 * 
	 * @param p
	 *            current {@link Plan}
	 */
	public synchronized void regWorkedPlan(Plan p) {
		LOG.debug(p.getPlanName() + " completed!");
		this.workedPlans++;
	}

	/**
	 * Retrieves if the whole working plans is completed
	 * 
	 * @return true if whole working plans completed
	 */
	public boolean isFinish() {
		boolean isFinish = false;
		if (this.workedPlans == this.workingPlans.size()) {
			isFinish = true;
		}
		return isFinish;
	}
}
