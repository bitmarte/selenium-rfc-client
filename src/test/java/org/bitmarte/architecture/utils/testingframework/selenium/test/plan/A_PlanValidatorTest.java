package org.bitmarte.architecture.utils.testingframework.selenium.test.plan;

import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl.PlanUnmarshaller;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * This class is the abstract PlanValidator
 *
 * @author bitmarte
 */
public abstract class A_PlanValidatorTest {

    protected static final Logger LOG = LoggerFactory.getLogger(A_PlanValidatorTest.class);

    protected ClassLoader classLoader = this.getClass().getClassLoader();
    protected static PlanUnmarshaller unmarshaller = null;
    protected File file = null;

    @BeforeClass
    public static void init() {
        unmarshaller = new PlanUnmarshaller();
    }

    protected void loadXmlFile(String filePath) {
        file = new File(classLoader.getResource(filePath).getFile());
    }

}
