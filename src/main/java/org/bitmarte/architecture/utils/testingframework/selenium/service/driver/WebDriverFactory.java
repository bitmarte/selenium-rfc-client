package org.bitmarte.architecture.utils.testingframework.selenium.service.driver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import org.bitmarte.architecture.utils.testingframework.selenium.beans.config.BrowserCapabilityConfig;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserCapabilityType;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserMode;
import org.bitmarte.architecture.utils.testingframework.selenium.constants.E_BrowserName;
import org.bitmarte.architecture.utils.testingframework.selenium.service.configuration.SeleniumConfigProvider;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * This is the Selenium web driver custom factory
 *
 * @author bitmarte
 */
public class WebDriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    /**
     * This is the main factory method that retrieve the {@link WebDriver}
     * instance
     *
     * @param browserMode {@link E_BrowserMode}
     * @param browserName {@link E_BrowserName}
     * @param proxy       {@link BrowserMobProxy}
     * @return the {@link WebDriver} instance
     * @throws Exception
     */
    public static WebDriver getInstance(String browserMode, String browserName, BrowserMobProxy proxy)
            throws Exception {
        E_BrowserMode e_BrowserMode = E_BrowserMode.LOCAL;
        E_BrowserName e_BrowserName = E_BrowserName.FIREFOX;
        try {
            e_BrowserMode = E_BrowserMode.valueOf(browserMode);
            e_BrowserName = E_BrowserName.valueOf(browserName);
        } catch (Exception e) {
            throw new WebDriverException("Unsupported testing mode for browserMode '" + browserMode
                    + "' and browserName '" + browserName + "'", e);
        }

        MutableCapabilities capabilities = getBrowserCapabilitiesFromConfiguration();

        // BrowserMobProxyServer
        if (proxy != null) {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        }

        switch (e_BrowserMode) {
            case REMOTE:
                switch (e_BrowserName) {
                    case CHROME:
                        LOG.info("using remote chrome browser on server '"
                                + SeleniumConfigProvider.getConfig().getSeleniumRcURL() + "'");
                        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                        // settings arguments
                        browserArgumentsConfig(E_BrowserName.CHROME, capabilities);
                        return new RemoteWebDriver(new URL(SeleniumConfigProvider.getConfig().getSeleniumRcURL()),
                                capabilities);
                    case IEXPLORER:
                        LOG.info("using remote iexplorer browser on server '"
                                + SeleniumConfigProvider.getConfig().getSeleniumRcURL() + "'");
                        capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                        return new RemoteWebDriver(new URL(SeleniumConfigProvider.getConfig().getSeleniumRcURL()),
                                capabilities);
                    case FIREFOX:
                        LOG.info("using remote firefox browser on server '"
                                + SeleniumConfigProvider.getConfig().getSeleniumRcURL() + "'");
                        capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                        // settings arguments
                        browserArgumentsConfig(E_BrowserName.FIREFOX, capabilities);
                        return new RemoteWebDriver(new URL(SeleniumConfigProvider.getConfig().getSeleniumRcURL()),
                                capabilities);

                    default:
                        throw new WebDriverException("Unknown case on E_BrowserName enum!");
                }
            case LOCAL:
                ChromeOptions chromeOptions = null;
                // Using local mode as default browserMode
                switch (e_BrowserName) {
                    case CHROME:
                        LOG.info("using local chrome browser");
                        System.setProperty("webdriver.chrome.driver",
                                SeleniumConfigProvider.getConfig().getLocalWebDriverPath());
                        // settings arguments
                        browserArgumentsConfig(E_BrowserName.CHROME, capabilities);
                        chromeOptions = new ChromeOptions();
                        chromeOptions.merge(capabilities);
                        return new ChromeDriver(chromeOptions);
                    case CHROME_HEADLESS:
                        LOG.info("using local chrome browser");
                        System.setProperty("webdriver.chrome.driver",
                                SeleniumConfigProvider.getConfig().getLocalWebDriverPath());
                        // settings arguments
                        browserArgumentsConfig(E_BrowserName.CHROME, capabilities);
                        chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("headless");
                        chromeOptions.merge(capabilities);
                        return new ChromeDriver(chromeOptions);
                    case IEXPLORER:
                        throw new WebDriverException("IExplorer browser in remote mode is not supported!");
                    default:
                        // Using Firefox as default local browser
                        LOG.info("using local firefox browser");
                        System.setProperty("webdriver.gecko.driver",
                                SeleniumConfigProvider.getConfig().getLocalWebDriverPath());
                        // settings arguments
                        browserArgumentsConfig(E_BrowserName.FIREFOX, capabilities);
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.merge(firefoxOptions);
                        return new FirefoxDriver(firefoxOptions);
                }

            default:
                throw new WebDriverException("Unknown case on E_BrowserMode enum!");
        }

    }

    /**
     * Configure arguments for WebDriver
     *
     * @param capabilities {@link MutableCapabilities}
     */
    private static void browserArgumentsConfig(E_BrowserName browserName, MutableCapabilities capabilities) {
        if (SeleniumConfigProvider.getConfig().getBrowser().getArguments() != null) {
            StringTokenizer args = new StringTokenizer(SeleniumConfigProvider.getConfig().getBrowser().getArguments(),
                    ",");

            switch (browserName) {
                case CHROME:
                    ChromeOptions chromeOpts = new ChromeOptions();
                    while (args.hasMoreTokens()) {
                        String arg = args.nextToken();
                        LOG.info("Setting argument for WebDriver: " + arg);
                        chromeOpts.addArguments(arg);
                    }
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOpts);
                    break;
                default:
                    FirefoxOptions firefoxOpts = new FirefoxOptions();
                    while (args.hasMoreTokens()) {
                        String arg = args.nextToken();
                        LOG.info("Setting argument for WebDriver: " + arg);
                        firefoxOpts.addArguments(arg);
                    }
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOpts);
                    break;
            }

        }
    }

    /**
     * Retrieve {@link MutableCapabilities} based on configuration, if it is
     * specified
     *
     * @return {@link MutableCapabilities}
     */
    private static MutableCapabilities getBrowserCapabilitiesFromConfiguration() {
        MutableCapabilities capabilities = new MutableCapabilities();
        if (SeleniumConfigProvider.getConfig().getBrowser().getBrowserCapabilities() != null) {
            for (BrowserCapabilityConfig capability : SeleniumConfigProvider.getConfig().getBrowser()
                    .getBrowserCapabilities()) {
                LOG.debug("adding capability '" + capability.getCapabilityName() + " = "
                        + capability.getCapabilityValue() + "'");
                switch (E_BrowserCapabilityType.valueOf(capability.getCapabilityType())) {
                    case BOOLEAN:
                        capabilities.setCapability(capability.getCapabilityName(),
                                Boolean.parseBoolean(capability.getCapabilityValue()));
                        break;
                    default:
                        capabilities.setCapability(capability.getCapabilityName(),
                                capability.getCapabilityValue().toString());
                        break;
                }
            }
        }

        return capabilities;
    }

}
