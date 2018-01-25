package org.bitmarte.architecture.utils.testingframework.selenium.test.config;

import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl.ConfigUnmarshaller;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * This class is the abstract ConfigValidator
 *
 * @author bitmarte
 */
public abstract class A_ConfigValidatorTest {

    protected static final Logger LOG = LoggerFactory.getLogger(A_ConfigValidatorTest.class);

    protected ClassLoader classLoader = this.getClass().getClassLoader();
    protected static ConfigUnmarshaller unmarshaller = null;
    protected File file = null;

    @BeforeClass
    public static void init() {
        unmarshaller = new ConfigUnmarshaller();
    }

    protected void loadXmlFile(String filePath) {
        file = new File(classLoader.getResource(filePath).getFile());
    }
    
}
