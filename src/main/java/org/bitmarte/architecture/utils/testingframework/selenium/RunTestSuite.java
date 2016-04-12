package org.bitmarte.architecture.utils.testingframework.selenium;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.Plan;
import org.bitmarte.architecture.utils.testingframework.selenium.driver.WebDriverFactory;
import org.bitmarte.architecture.utils.testingframework.selenium.reports.ReportProducer;
import org.bitmarte.architecture.utils.testingframework.selenium.setup.DefaultSeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * @author bitmarte
 *
 */
public class RunTestSuite {

	private static final Logger LOG = LoggerFactory.getLogger(RunTestSuite.class);

	public static void main(String[] args) throws Exception {
		WebDriver driver = null;
		List<Plan> workedPlans = new ArrayList<Plan>();
		BrowserMobProxy proxy = null;
		try {
			DefaultSeleniumConfig.loadConfiguration(args);

			if (DefaultSeleniumConfig.getConfig().isCleanReportBaseDirOnStart()) {
				cleanReportFolder();
			}

			File configFolder = new File(args[0] + "/plans");
			File[] plans = configFolder.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					boolean checker = false;
					if (StringUtils.equalsIgnoreCase(FilenameUtils.getExtension(pathname.getAbsolutePath()), "xml")) {
						LOG.debug("Load '" + pathname.getAbsolutePath() + "'");
						checker = true;
					}
					return checker;
				}
			});

			if (DefaultSeleniumConfig.getConfig().getMobProxy() != null) {
				proxy = new BrowserMobProxyServer();
			}

			driver = WebDriverFactory.getInstance(DefaultSeleniumConfig.getConfig().getBrowserMode(),
					DefaultSeleniumConfig.getConfig().getBrowserName(), proxy);

			if (plans.length > 0) {
				for (File file : plans) {
					workedPlans.add(PlanLoader.load(driver, file, proxy));
				}
			} else {
				LOG.warn("No plans exist!");
			}

		} catch (Exception e) {
			LOG.error("Generic error! ", e);
		} finally {
			ReportProducer.generateIndex(workedPlans);

			if (DefaultSeleniumConfig.getConfig().isCloseBrowserOnFinish()) {
				try {
					if (proxy != null) {
						proxy.stop();
					}
					driver.close();
				} catch (Exception e2) {
					LOG.error("WebDriver does not close correctly!");
				}
			}
		}
	}

	/**
	 * Clean report folder
	 */
	private static void cleanReportFolder() {
		File reportFolder = new File(DefaultSeleniumConfig.getConfig().getReportBaseDir());
		try {
			for (File file : reportFolder.listFiles()) {
				LOG.debug("removing file: " + file.getAbsolutePath());
				FileUtils.deleteQuietly(file);
			}
		} catch (Exception e) {
			LOG.error("error", e);
		}
	}
}
