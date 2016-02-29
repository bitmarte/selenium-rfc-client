package org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator;

import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl.ContainsContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl.EndWithContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl.EqualsContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl.RegexContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl.StartWithContentEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class ContentEvaluatorFactory {

	private static final Logger LOG = LoggerFactory
			.getLogger(ContentEvaluatorFactory.class);

	public static I_ContentEvaluator getInstance(String contentEvaluator) {
		if (contentEvaluator == null) {
			contentEvaluator = E_ContentEvaluator.EQUALS.name();
		}
		switch (E_ContentEvaluator.valueOf(contentEvaluator)) {
		case CONTAINS:
			return new ContainsContentEvaluator();
		case STARTWITH:
			return new StartWithContentEvaluator();
		case ENDWITH:
			return new EndWithContentEvaluator();
		case REGEX:
			return new RegexContentEvaluator();
		case EQUALS:
			return new EqualsContentEvaluator();

		default:
			LOG.info("Using default ContentEvaluator - EqualsContentEvaluator");
			return new EqualsContentEvaluator();
		}
	}
}
