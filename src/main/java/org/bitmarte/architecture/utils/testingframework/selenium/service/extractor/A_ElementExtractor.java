package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_ElementExtractor implements I_ElementExtractor {

	protected static final Logger LOG = LoggerFactory.getLogger(A_ElementExtractor.class);

	protected long getTimeoutPerElementExtrator() {
		return SeleniumConfigProvider.getConfig().getMaxTimeOutPerElementExtratorInSec();
	}

}
