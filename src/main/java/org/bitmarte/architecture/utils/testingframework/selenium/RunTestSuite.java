package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.driver.WebDriverFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bitmarte
 *
 */
public class RunTestSuite {

	private static final Logger LOG = LoggerFactory
			.getLogger(RunTestSuite.class);

	public static void main(String[] args) throws Exception {
		WebDriver driver = null;
		try {
			DefaultSeleniumConfig.loadConfiguration(args);

			cleanReportFolder();

			driver = WebDriverFactory.getInstance(DefaultSeleniumConfig
					.getConfig().getBrowserMode(), DefaultSeleniumConfig
					.getConfig().getBrowserName());

			File configFolder = new File(args[0] + "/plans");
			File[] plans = configFolder.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					boolean checker = false;
					if (StringUtils.equalsIgnoreCase(FilenameUtils
							.getExtension(pathname.getAbsolutePath()), "xml")) {
						LOG.debug("Load '" + pathname.getAbsolutePath() + "'");
						checker = true;
					}
					return checker;
				}
			});

			if (plans.length > 0) {
				for (File file : plans) {
					PlanLoader.load(driver, file);
				}
			} else {
				LOG.warn("No plans exist!");
			}

		} catch (Exception e) {
			LOG.error("Generic error! ", e);
		} finally {
			try {
				driver.close();
			} catch (Exception e2) {
				LOG.error("WebDriver does not close correctly!");
			}
		}
	}

	/**
	 * Clean report folder
	 */
	private static void cleanReportFolder() {
		File reportFolder = new File(DefaultSeleniumConfig.getConfig()
				.getReportBaseDir());
		try {
			for (File file : reportFolder.listFiles()) {
				LOG.debug("removing file: " + file.getAbsolutePath());
				FileUtils.deleteDirectory(file);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
