package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the generic plan loader, abstract class
 * 
 * @author bitmarte
 */
public abstract class A_PlanLoader implements I_PlanLoader {

	/**
	 * Absolute base path for test plan loading, passed from command line by
	 * args[0]
	 */
	protected String basePath = null;

	public A_PlanLoader(String basePath) {
		this.basePath = basePath;
	}

	protected static final Logger LOG = LoggerFactory.getLogger(A_PlanLoader.class);
}
