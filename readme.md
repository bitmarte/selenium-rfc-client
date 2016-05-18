# Selenium Run-From-Configuration client
This project allow you to create and run ent-to-end tests, running on real browser.<br/>
The artifact is a simple JAR file that you need to run directly from a shell; it requires only one argument, the path where you put configuration files (setup and plans).

[Two different approaches (local vs. remote)](docs/approaches.md)

<hr/>
<ul>
	<li>[Two different approaches (local vs. remote)](docs/approaches.md)</li>
	<ul>
		<li><a href="#local-approach">Local approach</a></li>
		<li><a href="#remote-approach">Remote approach</a></li>
	</ul>
	<li><a href="#client-setup">Client Setup</a></li>
	<li><a href="#make-your-plan">Make your plan</a></li>
	<li><a href="#execute-your-planplans-and-enjoy-it-">Execute your plan/plans and enjoy it :)</a></li>
	<li><a href="#ntlm-authentication">NTLM authentication</a></li>
	<li><a href="#advanced-success-condition-usage">Advanced success condition usage</a></li>
	<ul>
		<li><a href="#screenshotfilename">screenshotFileName</a></li>
		<li><a href="#waitbeforescreenshotinmillisec">waitBeforeScreenshotInMilliSec</a></li>
		<li><a href="#contentevaluator">contentEvaluator</a></li>
		<li><a href="#elementextractor">elementExtractor</a></li>
	</ul>
	<li><a href="#advanced-input-field-usage">Advanced input field usage</a></li>
	<ul>
		<li><a href="#type">type</a></li>
		<li><a href="#elementextractor">elementExtractor</a></li>
	</ul>
	<li><a href="#advanced-run-tags">Advanced run tags</a></li>
	<ul>
		<li><a href="#browseractions">BrowserActions</a></li>
	</ul>
	<li><a href="#webtimings-performance-monitoring">WebTimings performance monitoring</a></li>
	<ul>
		<li><a href="#advanced-webtimings-configuration">Advanced WebTimings configuration</a></li>
		<ul>
			<li><a href="#timeout-per-kpi-measure">Timeout per KPI measure</a></li>
			<li><a href="#kpi-interval-measure">KPI interval measure</a></li>
		</ul>
	</ul>
	<ul>
		<li><a href="#browsermob-proxy-bandwidth-throttling-and-more">BrowserMob Proxy (bandwidth throttling)</a></li>
		<ul>
			<li><a href="#generating-har-file">Generating HAR file</a></li>
		</ul>
	</ul>
	<li><a href="#concurrent-executors">Concurrent plan</a></li>
</ul>
<hr/>

## Two different approaches (local vs. remote)
It depends on your goal you can run a test on two different platforms: "local" or "remote".<br/>
**Don't worry, you can run the same test on both platforms.**<br/>

### Local approach
In this case the client execute tests on the same machine that you use to run it.<br/>
**You need to be sure that the browser used for testing is installed on you machine.**

### Remote approach
In this case the client execute tests on remote machine.<br/>
**You need to be sure that Selenium Standalone Server (http://docs.seleniumhq.org/download/) and the browser used for testing are installed on remote machine**.

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

## Make your plan
<ol>
	<li>Open "sample-plan.xml" or create new one (eg. /var/selenium/my-cfg/plans/sample-plan.xml)</li>
	<li>
		Let's create your first run! A run is an action (eg. click or form filling) with a success condition.
		You can find a run example below:
	</li>
</ol>	

	<plan cookiesRemoveAll="true">
		<run windowWidthPx="1440" windowHeightPx="900">
			<runName>001_gotoSeleniumHQ</runName>
			<url>http://www.seleniumhq.org/</url>
			<successCondition>
				<element>//h2</element>
				<elementContent>What is Selenium?</elementContent>
			</successCondition>
		</run>
		<run cookiesRemove="COOKIE_1,COOKIE_2">
			<runName>002_gotoDownload</runName>
			<url>http://www.seleniumhq.org/download/</url>
			<successCondition>
				<element>//h2</element>
				<elementContent>Downloads</elementContent>
			</successCondition>
		</run>
		<run cookiesRemoveAll="true" fullscreen="true">
			<runName>003_search</runName>
			<inputFields>
				<inputField>
					<element>//input[@name="q"]</element>
					<value>webdriver</value>
				</inputField>
			</inputFields>
			<browserActions>
				<browserAction>
					<action>CLICK</action>
					<elementByXPath>//input[@value="Go"]</elementByXPath>
				</browserAction>
			</browserActions>
			<successCondition elementExtractor="BY_ID" contentEvaluator="CONTAINS">
				<element>cse-footer</element>
				<elementContent>Google</elementContent>
			</successCondition>
		</run>
	</plan>
			
<ul>
	<li>'cookiesRemoveAll' is an **optional** attribute for plan and runs which removes all cookies</li>
	<li>'cookiesRemove' is an **optional** attribute for plan and runs which removes all passed cookies, comma separated values</li>
	<li>'fullscreen' is an **optional** attribute for plan and runs which maximize the window</li>
	<li>'windowWidthPx' is an **optional** attribute for runs where you put window width size in pixel. This attribute is not allowed if you are using fullscreen attribute in your plan</li>
	<li>'windowHeightPx' is an **optional** attribute for runs where you put window height size in pixel. This attribute is not allowed if you are using fullscreen attribute in your plan</li>
	<li>'runName' is a **require** node where you put the name (unique at all plan) and it used for screenshot naming</li>
	<li>'url' is an **optional** node where you put the "goToURL". **Remove this if it is not necessary for your run**</li>
	<li>'inputFields' is an **optional** list where you put your form filler. You must to use XPath for searching element and fill it with a value</li>
	<li>'browserActions' is an **optional** node where you put your actions (eg. click, refresh, etc...)</li>
	<li>'successCondition' is a **require** node where you put the element selection and the value to be checked</li>
</ul>

## Execute your plan/plans and enjoy it :)
You are ready to execute your plan, or plans.<br/>
Simple open your console (shell), point to your cloned repo (at pom.xml level) and call the command below:<br/>

	mvn -f pom.xml compile exec:java -Dexec.mainClass=org.bitmarte.architecture.utils.testingframework.selenium.RunTestSuite -Dexec.args="/var/selenium/my-cfg"
	
**Pay attention: specify your base config path as the only require java argument ("/var/selenium/my-cfg" for this tutorial)**

<br/><br/><hr/>
## NTLM authentication
You can use NTLM authentication directly in your every runs which it need.

	<run>
		<runName>001_ntlm&lt/runName>
		...
		<authentication authType="NTLM" waitPromptInSec="5">
			<username>MY_USERNAME</username>
			<password>MY_PASSWORD</password>
		</authentication>
		...
		<successCondition>
			<element>//h2</element>
			<elementContent>What is Selenium?</elementContent>
		</successCondition>
	</run>

Optionally you can use a custom attribute for setting up the NTLM prompt alert waiting time (waitPromptInSec):

	<run>
		<runName>001_ntlm&lt/runName>
		...
		<authentication authType="NTLM" waitPromptInSec="5">
			<username>MY_USERNAME</username>
			<password>MY_PASSWORD</password>
		</authentication>
		...
	</run>

The default value is 5 sec.
<br/><br/>
## Advanced success condition usage
You can use some advanced matchers at <successCondition> node in order to use make your test more powerful.

### screenshotFileName
You can specify a custom screenshot filename on success condition
Here you can find an example:

	<successCondition screenshotFileName="myScreenshotName">
		<element>//h5</element>
		<elementContent>MyContent</elementContent>
	</successCondition>

### waitBeforeScreenshotInMilliSec
You can specify a sleep time after success condition evaluator before take the screenshot.
Here you can find an example:

	<successCondition waitBeforeScreenshotInMilliSec="1000">
		<element>//h5</element>
		<elementContent>MyContent</elementContent>
	</successCondition>

### contentEvaluator
You can see the allowed values for this attribute:

| Attribute value        		| Description										|
| ----------------------------- | ------------------------------------------------- |
| STARTWITH						| StringUtils.startWithIgnoreCase will be use		|
| ENDWITH						| StringUtils.endWithIgnoreCase will be use			|
| CONTAINS						| StringUtils.containsIgnoreCase will be use		|
| REGEX							| Regex pattern matcher will be use					|
| EQUALS						| StringUtils.equalsIgnoreCase						|

Here you can find an example that you can use for matching all numbers inside an "h5" html tag:

	<successCondition contentEvaluator="REGEX">
		<element>//h5</element>
		<elementContent>[0-9]</elementContent>
	</successCondition>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: StringUtils.equalsIgnoreCase**
<br/><br/>
### elementExtractor
You can see the allowed values for this attribute:

| Attribute value        		| Description																|
| ----------------------------- | ------------------------------------------------------------------------- |
| BY_ID							| Using HTML "id" attribute for finding element								|
| BY_XPATH						| Using XPath for finding element, all matched elements will be returned	|
| BY_CLASSNAME					| Using HTML "class" attribute for finding element							|

Here you can find an example that you can use for matching all numbers inside an html element with "myName" ID value:

	<successCondition contentEvaluator="REGEX" elementExtractor="BY_ID">
		<element>myName</element>
		<elementContent>[0-9]</elementContent>
	</successCondition>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: BY_XPATH**
<br/><br/>
## Advanced input field usage
You can use some advanced matchers at <inputField> node in order to use make your test more powerful.

### type
You can see the allowed values for this attribute:

| Attribute value        		| Description																|
| ----------------------------- | ------------------------------------------------------------------------- |
| TEXT							| For input type text field													|
| SELECT						| For select field (combo box), value based for setting						|
| RADIO							| For input type radio field												|

Here you can find an example that you can use for filling value "myValue" into an html element with "myName" ID value:

	<inputField type="SELECT">
		<element>myName</element>
		<value>myValue</value>
	</inputField>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: TEXT**
<br/><br/>

### elementExtractor
You can see the allowed values for this attribute:

| Attribute value        		| Description																|
| ----------------------------- | ------------------------------------------------------------------------- |
| BY_ID							| Using HTML "id" attribute for finding element								|
| BY_XPATH						| Using XPath for finding element, all matched elements will be returned	|
| BY_CLASSNAME					| Using HTML "class" attribute for finding element							|

Here you can find an example that you can use for filling value "myValue" into an html element with "myName" ID value:

	<inputField elementExtractor="BY_ID">
		<element>myName</element>
		<value>myValue</value>
	</inputField>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: BY_XPATH**
<br/><br/>
## Advanced run tags
There are some run tags (optionals) that you can use for advanced behaviors in your tests.

### BrowserActions
You can use <browserActions> tag for simulating a user action. The node contains a list of actions it will be executed in the specified order that you append its children.
By default browserActions will be excetuted as last task, before successCondition's evaluate, but you can force its excecution as first operation on a run with a specific attribute 'firstAction', boolean value (default value is false).
With 'waitBeforeActionInMillis' attribute you can configure a wait time for the action (time in ms).

| Action value     		   		| Description																				|
| ----------------------------- | ----------------------------------------------------------------------------------------- |
| REFRESH						| Refresh current page (reload)																|
| BACK							| Go back to previous page																	|
| FORWARD						| Go ahead 																					|
| CLICK							| Make a click on an html element (elementByXPath attribute is required)					|
| IFRAME_SWITCH					| Switch to iframe at a given XPath, the first one (elementByXPath attribute is required)	|

	<run>
		<runName>001_refreshPage&lt/runName>
		<browserAction action="REFRESH"/>
		<successCondition>
			<element>//h2</element>
			<elementContent>What is Selenium?</elementContent>
		</successCondition>
	</run>
	...
	<run>
		<runName>002_clickThere&lt/runName>
		<browserActions>
			<browserAction firstAction="true" waitBeforeActionInMillis="200">
				<action>CLICK</action>
				<elementByXPath>//input[@value="Go"]</elementByXPath>
			</browserAction>
			<browserAction>
				<action>CLICK</action>
				<elementByXPath>//input[@value="There"]</elementByXPath>
			</browserAction>
		</browserActions>
		<successCondition>
			<element>//h2</element>
			<elementContent>What is Selenium?</elementContent>
		</successCondition>
	</run>

**Pay attention: <browserAction> tag is the first task that will be execute on your run, so you in some cases you need to separate your conceptual test run in more than one**
<br/><br/>
## WebTimings performance monitoring
You can monitor the frontend (client) performance using WebTimings API (https://www.w3.org/TR/navigation-timing-2/#h-processing-model) and read it into the final report.
Here the KPIs that you can check:

| KPI			        		| Description																								|
| ----------------------------- | --------------------------------------------------------------------------------------------------------- |
| TOTAL REQUESTS				| The number of entries that browser retrieve																|
| UPLOAD TIME					| The time spent for the requests (msec) [responseStart - requestStart]										|
| DOWNLOAD TIME					| The time spent for retrieving the resources (msec) [responseEnd - responseStart]							|
| BROWSER PROCESSING TIME		| The time spent by your browser for managing response (msec) [domContentLoadEventEnd - responseEnd]		|
| TOTAL TIME					| The time spent by your browser for managing the page call (msec) [domContentLoadEventEnd - requestStart]	|

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings/>
	...
	</config>

**Pay attention: WebTimings API can not be activate on Internet Explorer browser**

### Advanced WebTimings configuration
There are some advanced settings for KPIs performance monitoring
#### Timeout per KPI measure
The timeout if the KPI measures are always different each other.

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings>
		<maxTimeoutPerMeasureInSec>10</maxTimeoutPerMeasureInSec>
	</webTimings>
	...
	</config>

The default value is 5sec.
#### KPI interval measure
The time (in sec) between two KPI measures.

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings>
		<kpiIntervalMeasureInSec>1</kpiIntervalMeasureInSec>
	</webTimings>
	...
	</config>

The default value is 0sec, no delay.
<br/><br/>
## BrowserMob Proxy (bandwidth throttling and more)
You can use BrowserMob-Proxy (https://github.com/lightbody/browsermob-proxy) in order to introduce a light-proxy in your tests.
So you will have some features as configuration (config.xml), here in the table below (see BrowserMobProxy interface on github project):

| FEATURE	        		| Required (default)	| Description																								|
| ------------------------- | ---------------------	|---------------------------------------------------------------------------------------------------------- |
| chainedProxy				|	NO (no proxy)		| Sets an upstream proxy that this proxy will use to connect to external hosts (eg. hostname:port)			|
| port						|	NO (0)				| Starts the proxy on the specified port																	|
| downloadBytePerSec		|	NO (no limit)		| The time spent for the requests (msec) [responseStart - requestStart]										|
| uploadBytePerSec			| 	NO (no limit)		| The time spent for retrieving the resources (msec) [responseEnd - responseStart]							|
| latencyInMillisec			| 	NO (no limit)		| Network latency																							|

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<mobProxy>
		<chainedProxy>my.host.proxy:9090</chainedProxy>
		<uploadBytePerSec>1048576</uploadBytePerSec>
		<downloadBytePerSec>7340032</downloadBytePerSec>
	</mobProxy>
	...
	</config>

### Generating HAR file
You can produce an HAR file (one file for each page) with a simple additional attribute, boolean value.

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<mobProxy enableHarCapture="true"/>
	...
	</config>

## Advanced plan execution

### Run a plan more times
You can use this attribute in order to execute the same plan more times.

	<plan executions="5">
	...
	</plan>

### Concurrent execution
You can use this node in order to execute plans in parallel.

	<config>
		...
		<concurrentPlans>true</concurrentPlans>
	</config>