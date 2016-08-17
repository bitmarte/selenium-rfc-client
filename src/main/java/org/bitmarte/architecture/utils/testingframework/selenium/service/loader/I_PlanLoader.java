package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import java.util.List;

import org.bitmarte.architecture.utils.testingframework.selenium.beans.plan.Plan;

/**
 * @author bitmarte
 *
 */
public interface I_PlanLoader {

	public List<Plan> loadPlans() throws Exception;
}
