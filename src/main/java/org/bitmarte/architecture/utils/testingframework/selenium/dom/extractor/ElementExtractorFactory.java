package org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor;

import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_ElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.impl.ByClassNameElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.impl.ByIdElementExtractor;
import org.bitmarte.architecture.utils.testingframework.selenium.dom.extractor.impl.ByXpathElementExtractor;

/**
 * @author bitmarte
 *
 */
public class ElementExtractorFactory {

	public static I_ElementExtractor getInstance(String elementExtractor) {
		E_ElementExtractor e_ElementExtractor = E_ElementExtractor.BY_XPATH;
		try {
			e_ElementExtractor = E_ElementExtractor.valueOf(elementExtractor);
		} catch (Exception e) {
			// TODO: handle exception
		}

		switch (e_ElementExtractor) {
		case BY_XPATH:
			return new ByXpathElementExtractor();
		case BY_ID:
			return new ByIdElementExtractor();
		case BY_CLASSNAME:
			return new ByClassNameElementExtractor();

		default:
			return new ByXpathElementExtractor();
		}
	}
}
