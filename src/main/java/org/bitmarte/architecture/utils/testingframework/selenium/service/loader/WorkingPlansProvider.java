package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.bitmarte.architecture.utils.testingframework.selenium.service.executor.plan.WorkingPlans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class WorkingPlansProvider {

	protected static final Logger LOG = LoggerFactory.getLogger(WorkingPlansProvider.class);

	public static WorkingPlans getWorkingPlans(String basePath) throws Exception {
		WorkingPlans workingPlans = new WorkingPlans();

		for (String planLoader : SeleniumConfigProvider.getConfig().getCustomPlanLoaders()) {
			try {
				LOG.info("loading plans [" + planLoader + "] ...");
				workingPlans.addAllPlans(
						PlanLoaderFactory.getInstance(E_PlanLoader.valueOf(planLoader), basePath).loadPlans());
			} catch (Exception e) {
				throw e;
			}
		}

		if (workingPlans.getPlans().isEmpty()) {
			throw new Exception("No plans loaded at all!");
		}

		return workingPlans;
	}
}
