package org.bitmarte.architecture.utils.testingframework.selenium.test.plan.impl.done;

import org.bitmarte.architecture.utils.testingframework.selenium.test.plan.A_PlanValidatorTest;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test PlanValidator
 *
 * @author bitmarte
 */
public class Sample1Test extends A_PlanValidatorTest {

    @Before
    public void load() {
        super.loadXmlFile("plan/done/sample1.xml");
    }

    @Test
    public void configValidatorTest() throws Exception {
        unmarshaller.unmarshall(file);
    }

}
