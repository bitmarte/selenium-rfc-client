package org.bitmarte.architecture.utils.testingframework.selenium.service.extractor;

import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl.ByClassNameElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl.ByIdElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.service.extractor.impl.ByXpathElementExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the concrete element extractor factory
 * 
 * @author bitmarte
 */
public class ElementExtractorFactory {

	private static final Logger LOG = LoggerFactory.getLogger(ElementExtractorFactory.class);

	/**
	 * This is the factory method, it retrieves the right instance
	 * 
	 * @param elementExtractor
	 * @return
	 */
	public static I_ElementExtractor getInstance(String elementExtractor) {
		if (elementExtractor == null) {
			elementExtractor = E_ElementExtractor.BY_XPATH.name();
		}
		switch (E_ElementExtractor.valueOf(elementExtractor)) {
		case BY_XPATH:
			return new ByXpathElementExtractor();
		case BY_ID:
			return new ByIdElementExtractor();
		case BY_CLASSNAME:
			return new ByClassNameElementExtractor();

		default:
			LOG.info("Using default ElementExtractor - ByXpathElementExtractor");
			return new ByXpathElementExtractor();
		}
	}
}
