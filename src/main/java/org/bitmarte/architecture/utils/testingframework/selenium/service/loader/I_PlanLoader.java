package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;

/**
 * This is the plan loader interface. Extending {@link A_PlanLoader} allows you
 * to get/load the test suite, from different formats
 * 
 * @author bitmarte
 */
public interface I_PlanLoader {

	/**
	 * Loads all test plans
	 * 
	 * @return whole test suite for current run
	 * @throws Exception
	 */
	public List<Plan> loadPlans() throws Exception;
}
