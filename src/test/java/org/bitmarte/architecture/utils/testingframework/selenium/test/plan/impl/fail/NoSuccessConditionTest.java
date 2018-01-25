package org.bitmarte.architecture.utils.testingframework.selenium.test.plan.impl.fail;

import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;
import org.bitmarte.architecture.utils.testingframework.selenium.test.plan.A_PlanValidatorTest;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test PlanValidator
 *
 * @author bitmarte
 */
public class NoSuccessConditionTest extends A_PlanValidatorTest {

    @Before
    public void load() {
        super.loadXmlFile("plan/fail/noSuccessCondition.xml");
    }

    @Test(expected = ValidatorException.class)
    public void configValidatorTest() throws Exception {
        unmarshaller.unmarshall(file);
    }

}
