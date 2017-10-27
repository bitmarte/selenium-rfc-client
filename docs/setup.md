## Client Setup
<ol>
	<li>Clone this repo</li>
	<li>Choose a path where you want to put your configuration, and create it on your local machine (eg. /var/selenium/my-cfg)</li>
	<li>Copy to your preferred path all "src/main/examples/" content (config.xml and plans folder with its content)</li>
	<li>
		Open "config.xml" (eg. /var/selenium/my-cfg/config.xml) and apply your configuration, as the example below:
	</li>
</ol>
	
	<config>
		<browser>
			<mode>LOCAL</mode>
			<name>CHROME</name>
			<arguments>--disable-http2</arguments>
			<browserCapability capabilityType="BOOLEAN">
			<capabilityName>acceptInsecureCerts</capabilityName>
				<capabilityValue>true</capabilityValue>
			</browserCapability>
		</browser>
		<localWebDriverPath>/path/to/browser-web-driver</localWebDriverPath>
		<seleniumRcURL>http://10.217.xx.xx:4444/wd/hub</seleniumRcURL>
		<reportBaseDir>/var/tmp/selenium/reports/</reportBaseDir>
		<errorConditions>
			<errorCondition elementExtractor="BY_CLASSNAME" contentEvaluator="CONTAINS">
				<element>error-msg-container</element>
				<elementContent>error-msg-content</elementContent>
			</errorCondition>
		</errorConditions>
		<waitBeforeScreenshotInMilliSec>10</waitBeforeScreenshotInMilliSec>
	</config>

* 'browser/mode' is **require** node where put the test approach. **Take a look below for supported browsers**
* 'browser/name' is a **require** node where put the browser that you want to run tests on. **Take a look below for supported browsers**
* 'browser/arguments' is an **optional** node where put the webdriver arguments, just Chrome and Firefox supported (http://peter.sh/experiments/chromium-command-line-switches/)
* 'browser/browserCapability' is an **optional** list where put DesiredCapabilities of your selected browser, just Chrome and Firefox supported
* 'localWebDriverPath' is a **required** node where put the whole webdriver path, even filename
* 'seleniumRcURL' is an **optional** node where put the SeleniumRC URL connection. **Remove this node for LOCAL approach**
* 'browserActionExecutor' is an **optional** node where put the default browser action executor configurations, such as 'waitBeforeFirstActionInMs' and 'waitBeforeRetryActionInMs'. **Take a look below for the documentation**
* 'maxTimeOutPerElementExtratorInSec' is an **optional** node where put the timeout (in second) user for element extractors. The default value is 5sec. You can override it for each run, take a look at [Extractor](extractor.md)
* 'pollingPerElementExtractorInMillisec' is an **optional** node where put the polling (in millisecond) used during element extractor. The default value is 250msec.
* 'customPlanLoaders' is an **optional** node where put your customPlanLoaders implementations. **Take a look below for supported customPlanLoaders**
* 'reportBaseDir' is a **require** node where put your preferred location where Selenium save reports
* 'cleanReportBaseDirOnStart' is an **optional** node used to clean the location where Selenium save reports before run. The default value is true
* 'errorConditions' is a **require** node where put your generic error conditions
* 'closeBrowserOnFinish' is an **optional** node used to close browser at the end of your test, boolean value. The default value is true
* 'waitBeforeScreenshotInMilliSec' is an **optional** node used to apply a sleep time after SuccessCondition evaluator before take the screenshot. You can override it for each condition. The dafault value is 0ms
* 'inViewScreenshot' is an **optional** node (boolean value) that it allows you to save the whole page, not only in view one. The default value is true
* 'concurrentPlans' is an **optional** node for allowing concurrent tests with default value false. For more details see [Concurrent plan](docs/concurrent-plan.md)

### browser
#### browserMode

| Attribute value        		| Description										|
| ----------------------------- | ------------------------------------------------- |
| LOCAL							| Running local browser instance					|
| REMOTE						| Running remote browser instance (a)				|

(a) You need to install Selenium Standalone Server (http://docs.seleniumhq.org/download/), configure it with the right jar agruments
and put the right URL for property <seleniumRcURL> in your config.xml file for running your remote browser instance

#### browserName

| Attribute value        		| Description															|
| ----------------------------- | --------------------------------------------------------------------- |
| FIREFOX						| Using firefox browser instance, only 48.x and later supported			|
| CHROME						| Using chrome browser instance											|
| IEXPLORER						| Using iexplorer browser instance, only remote mode is allowed (b)		|

(b) You need to download Internet Explorer Driver Server (http://www.seleniumhq.org/download/) and passing the '-Dwebdriver.ie.driver' jar argument point to iexplorer driver on your remote machine.
Pay attention that all IExplorer security zone must be the same and the zoom must be setup to 100%

#### browserCapability/capabilityType

| Attribute value        		| Description																		|
| ----------------------------- | --------------------------------------------------------------------------------- |
| STRING						| Using DesiredCapabilities.setCapability(String capabilityName, String value)		|
| BOOLEAN						| Using DesiredCapabilities.setCapability(String capabilityName, boolean value)		|

### customPlanLoaders
Add a custom plan loader implementation list to extends it and to allow a different plan input or generator:

| Attribute value        		| Description																												|
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| CHROME_EXTESION_JSON			| Load plans generated by [selenium-rfc-chrome-extension](https://github.com/bitmarte/selenium-rfc-chrome-extension)		|

Eg.

	<customPlanLoaders>
		<string>CHROME_EXTESION_JSON</string>
	</customPlanLoaders>
	
### browserActionExecutor
In order to customize your test run configuration you can override two important waiting time.
With these two parameters you can drive browser action executors in order to introduce a dalay/sleep time before the first tentative and the retry one.
So, when the executor try to make the action (using 'waitBeforeFirstActionInMs') and it fails, it retries the last one (using 'waitBeforeRetryActionInMs'):

| Attribute value        		| Description																| Default	|
| ----------------------------- | ------------------------------------------------------------------------- |-----------|
| waitBeforeFirstActionInMs		| Waiting time before it hits the first tentative on browserActionExecutor	| 100		|
| waitBeforeRetryActionInMs		| Waiting time before it hits the retry tentative on browserActionExecutor	| 300		|


Eg.

	<browserActionExecutor>
		<waitBeforeFirstActionInMs>500</waitBeforeFirstActionInMs>
		<waitBeforeRetryActionInMs>800</waitBeforeRetryActionInMs>
	</browserActionExecutor>