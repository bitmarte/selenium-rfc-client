package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan.WorkingPlans;

/**
 * @author bitmarte
 *
 */
public interface I_PlanLoader {

	public WorkingPlans loadWorkingPlans() throws Exception;
}
