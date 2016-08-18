package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public abstract class A_ElementExtractor implements I_ElementExtractor {

	protected static final Logger LOG = LoggerFactory.getLogger(A_ElementExtractor.class);

	private static final long WAIT_FOR_RETRIEVING_ELEMENT_DEFAULT = 5000;

	protected long getWaitingTime() {
		return WAIT_FOR_RETRIEVING_ELEMENT_DEFAULT;
	}

}
