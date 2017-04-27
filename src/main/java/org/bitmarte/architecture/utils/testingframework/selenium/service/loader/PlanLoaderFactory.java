package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl.ChromeExtensionPlanLoader;
import org.bitmarte.architecture.utils.testingframework.selenium.service.loader.impl.DefaultPlanLoader;

/**
 * This is the plan loader factory
 * 
 * @author bitmarte
 */
public class PlanLoaderFactory {

	/**
	 * The factory method
	 * 
	 * @param planLoader
	 *            {@link E_PlanLoader} type
	 * @param basePath
	 *            the base path {@link String}
	 * @return the plan loader implementation of {@link I_PlanLoader}
	 * @throws Exception
	 */
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
