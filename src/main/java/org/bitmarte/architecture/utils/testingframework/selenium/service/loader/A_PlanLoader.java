package org.bitmarte.architecture.utils.testingframework.selenium.service.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_PlanLoader implements I_PlanLoader {

	protected String basePath = null;

	public A_PlanLoader(String basePath) {
		this.basePath = basePath;
	}

	protected static final Logger LOG = LoggerFactory.getLogger(A_PlanLoader.class);
}
