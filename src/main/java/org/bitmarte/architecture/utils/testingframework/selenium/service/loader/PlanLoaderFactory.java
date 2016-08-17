package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl.ChromeExtensionPlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl.DefaultPlanLoader;

/**
 * @author bitmarte
 *
 */
public class PlanLoaderFactory {

	public static I_PlanLoader getInstance(E_PlanLoader planLoader, String basePath) throws Exception {
		try {
			switch (planLoader) {
			case CHROME_EXTESION_JSON:
				return new ChromeExtensionPlanLoader(basePath);
			default:
				return new DefaultPlanLoader(basePath);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
