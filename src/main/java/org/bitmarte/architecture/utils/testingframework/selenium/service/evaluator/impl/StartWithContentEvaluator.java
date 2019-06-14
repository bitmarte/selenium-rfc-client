package org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.impl;

import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.A_ContentEvaluator;
import org.bitmarte.architecture.utils.testingframework.selenium.service.evaluator.exceptions.ContentEvaluatorException;

/**
 * This evaluator checks if the content start with a specific string
 *
 * @author bitmarte
 */
public class StartWithContentEvaluator extends A_ContentEvaluator {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.bitmarte.architecture.utils.testingframework.selenium.dom.evaluator.
     * I_ContentEvaluator#evaluate(java .lang.String, java.lang.String)
     */
    public boolean evaluate(String str1, String str2) throws ContentEvaluatorException {
        LOG.debug("using StartWithContentEvaluator...");
        boolean result = false;
        if (StringUtils.startsWithIgnoreCase(str2, str1)) {
            result = true;
        }
        if (!result) {
            throw new ContentEvaluatorException("StartWithContentEvaluator failed: '" + str2 + "' does not start with '" + str1 + "'");
        }
        return result;
    }

}
