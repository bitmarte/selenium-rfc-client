package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl.DefaultPlanLoader;

/**
 * @author bitmarte
 *
 */
public class PlanLoaderFactory {

	public static I_PlanLoader getInstance(String basePath) throws Exception {
		try {
			return new DefaultPlanLoader(basePath);
		} catch (Exception e) {
			throw e;
		}
	}
}
