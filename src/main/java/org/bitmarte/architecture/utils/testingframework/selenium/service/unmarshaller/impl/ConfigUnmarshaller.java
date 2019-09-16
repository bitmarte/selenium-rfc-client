package org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.impl;

import com.thoughtworks.xstream.XStream;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.*;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.run.ErrorCondition;
import org.bitmarte.architecture.utils.testingframework.selenium.service.unmarshaller.A_Unmarshaller;
import org.bitmarte.architecture.utils.testingframework.selenium.service.validator.ValidatorHandler;

import java.io.File;

/**
 * @author bitmarte
 */
public class ConfigUnmarshaller extends A_Unmarshaller {

    public Config unmarshall(File xmlFileInput) throws Exception {
        try {
            XStream xStream = new XStream();

            xStream.processAnnotations(Config.class);
            xStream.processAnnotations(ReportConfig.class);
            xStream.processAnnotations(ErrorCondition.class);
            xStream.processAnnotations(BrowserConfig.class);
            xStream.processAnnotations(BrowserActionExecutorConfig.class);
            xStream.processAnnotations(BrowserCapabilityConfig.class);
            xStream.processAnnotations(MobProxyConfig.class);
            xStream.processAnnotations(WebTimingsConfig.class);

            Config config = (Config) xStream.fromXML(xmlFileInput);

            ValidatorHandler.execute(config);

            return config;
        } catch (Exception e) {
            throw e;
        }
    }

}
