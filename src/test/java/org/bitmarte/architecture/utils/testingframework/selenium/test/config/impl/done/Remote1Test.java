package org.bitmarte.architecture.utils.testingframework.selenium.test.config.impl.done;

import org.bitmarte.architecture.utils.testingframework.selenium.test.config.A_ConfigValidatorTest;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test ConfigValidator
 *
 * @author bitmarte
 */
public class Remote1Test extends A_ConfigValidatorTest {

    @Before
    public void load() {
        super.loadXmlFile("config/done/remote1.xml");
    }

    @Test
    public void configValidatorTest() throws Exception {
        unmarshaller.unmarshall(file);
    }

}
