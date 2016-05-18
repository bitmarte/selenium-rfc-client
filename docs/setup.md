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
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
		<seleniumRcURL>http://10.217.xx.xx:4444/wd/hub</seleniumRcURL>
		<reportBaseDir>/var/tmp/selenium/reports/</reportBaseDir>
		<cleanReportBaseDirOnStart>true</cleanReportBaseDirOnStart>
		<errorConditions>
			<errorCondition elementExtractor="BY_CLASSNAME" contentEvaluator="CONTAINS">
				<element>error-msg-container</element>
				<elementContent>error-msg-content</elementContent>
			</errorCondition>
		</errorConditions>
		<closeBrowserOnFinish>false</closeBrowserOnFinish>
	</config>

<ul>
	<li>'browserMode' is **require** node where you put the test approach. **Take as look below for supported browsers**</li>
	<li>'browserName' is a **require** node where you put the browser that you want to run tests on. **Take as look below for supported browsers**</li>
	<li>'localWebDriverPath' is an **optional** node where you put the webdriver path, only if you are not using Firefox</li>
	<li>'seleniumRcURL' is an **optional** node where you put the SeleniumRC URL connection. **Remove this node for LOCAL approach**</li>
	<li>'maxTimeOutPerSuccessConditionInSec' is an **optional** node where you put your waiting timeout in second that it used for checking your success condition. The default value is 10 sec.</li>
	<li>'maxTimeOutPerErrorConditionInSec' is an "optional" node where you put your waiting timeout in second that it used for checking yours error condition. The default value is 2 sec.</li>
	<li>'reportBaseDir' is a **require** node where you put your preferred location where Selenium save reports</li>
	<li>'cleanReportBaseDirOnStart' is an **optional** node used to clean the location where Selenium save reports before run. The default value is false</li>
	<li>'errorConditions' is an **optional** node where you put your generic error conditions. The elementContent node is an **optional** one</li>
	<li>'closeBrowserOnFinish' is an **optional** node used to close browser at the end of your test, boolean value. The default value is false</li>
</ul>

You can see the allowed values for <browserMode> attribute:

| Attribute value        		| Description										|
| ----------------------------- | ------------------------------------------------- |
| LOCAL							| Running local browser instance					|
| REMOTE						| Running remote browser instance (a)				|

(a) You need to install Selenium Standalone Server (http://docs.seleniumhq.org/download/), configure it with the right jar agruments
and put the right URL for property <seleniumRcURL> in your config.xml file for running your remote browser instance

You can see the allowed values for <browserName> attribute:

| Attribute value        		| Description															|
| ----------------------------- | --------------------------------------------------------------------- |
| FIREFOX						| Using firefox browser instance										|
| CHROME						| Using chrome browser instance											|
| IEXPLORER						| Using iexplorer browser instance, only remote mode is allowed (b)		|

(b) You need to download Internet Explorer Driver Server (http://www.seleniumhq.org/download/) and passing the '-Dwebdriver.ie.driver' jar argument point to iexplorer driver on your remote machine.
Pay attention that all IExplorer security zone must be the same and the zoom must be setup to 100%