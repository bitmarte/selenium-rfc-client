package org.bitmarte.architecture.utils.testingframework.selenium.test.config.impl.fail;

import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.exceptions.ValidatorException;
import org.bitmarte.architecture.utils.testingframework.selenium.test.config.A_ConfigValidatorTest;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test ConfigValidator
 *
 * @author bitmarte
 */
public class NoBrowserTest extends A_ConfigValidatorTest {

    @Before
    public void load() {
        super.loadXmlFile("config/fail/noBrowser.xml");
    }

    @Test(expected = ValidatorException.class)
    public void configValidatorTest() throws Exception {
        unmarshaller.unmarshall(file);
    }

}
