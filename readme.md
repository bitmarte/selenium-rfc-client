# Selenium Run-From-Configuration client
This project allow you to create and run ent-to-end tests, running on real browser.<br/>
The artifact is a simple JAR file that you need to run directly from a shell; it requires only one argument, the path where you put configuration files (setup and plans).

<hr/>
<ul>
	<li><a href="#two-different-approaches-local-vs-remote">Two different approaches (local vs. remote)</a></li>
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
		<li><a href="#browseraction">BrowserAction</a></li>
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
		<pre>
			<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
	&lt;seleniumRcURL&gt;http://10.217.xx.xx:4444/wd/hub&lt;/seleniumRcURL&gt;
	&lt;reportBaseDir&gt;/var/tmp/selenium/reports/&lt;/reportBaseDir&gt;
	&lt;cleanReportBaseDirOnStart&gt;true&lt;/cleanReportBaseDirOnStart&gt;
	&lt;errorConditions&gt;
		&lt;errorCondition elementExtractor="BY_CLASSNAME" contentEvaluator="CONTAINS"&gt;
			&lt;element&gt;error-msg-container&lt;/element&gt;
			&lt;elementContent&gt;error-msg-content&lt;/elementContent&gt;
		&lt;/errorCondition&gt;
	&lt;/errorConditions&gt;
	&lt;closeBrowserOnFinish&gt;false&lt;/closeBrowserOnFinish&gt;
&lt;/config&gt;
			</code>
		</pre>
		<ul>
			<li>&lt;browserMode&gt; is **require** node where you put the test approach. **Take as look below for supported browsers**</li>
			<li>&lt;browserName&gt; is a **require** node where you put the browser that you want to run tests on. **Take as look below for supported browsers**</li>
			<li>&lt;localWebDriverPath&gt; is an **optional** node where you put the webdriver path, only if you are not using Firefox</li>
			<li>&lt;seleniumRcURL&gt; is an **optional** node where you put the SeleniumRC URL connection. **Remove this node for LOCAL approach**</li>
			<li>&lt;maxTimeOutPerSuccessConditionInSec&gt; is an **optional** node where you put your waiting timeout in second that it used for checking your success condition. The default value is 10 sec.</li>
			<li>&lt;maxTimeOutPerErrorConditionInSec&gt; is an "optional" node where you put your waiting timeout in second that it used for checking yours error condition. The default value is 2 sec.</li>
			<li>&lt;reportBaseDir&gt; is a **require** node where you put your preferred location where Selenium save reports</li>
			<li>&lt;cleanReportBaseDirOnStart&gt; is an **optional** node used to clean the location where Selenium save reports before run. The default value is false</li>
			<li>&lt;errorConditions&gt; is an **optional** node where you put your generic error conditions. The elementContent node is an **optional** one</li>
			<li>&lt;closeBrowserOnFinish&gt; is an **optional** node used to close browser at the end of your test, boolean value. The default value is false</li>
		</ul>
	</li>
</ol>

You can see the allowed values for &lt;browserMode&gt; attribute:

| Attribute value        		| Description										|
| ----------------------------- | ------------------------------------------------- |
| LOCAL							| Running local browser instance					|
| REMOTE						| Running remote browser instance (a)				|

(a) You need to install Selenium Standalone Server (http://docs.seleniumhq.org/download/), configure it with the right jar agruments
and put the right URL for property &lt;seleniumRcURL&gt; in your config.xml file for running your remote browser instance

You can see the allowed values for &lt;browserName&gt; attribute:

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
		<pre>
			<code>
&lt;plan cookiesRemoveAll="true"&gt;
	&lt;run windowWidthPx="1440" windowHeightPx="900"&gt;
		&lt;runName&gt;001_gotoSeleniumHQ&lt;/runName&gt;
		&lt;url&gt;http://www.seleniumhq.org/&lt;/url&gt;
		&lt;successCondition&gt;
			&lt;element&gt;//h2&lt;/element&gt;
			&lt;elementContent&gt;What is Selenium?&lt;/elementContent&gt;
		&lt;/successCondition&gt;
	&lt;/run&gt;
	&lt;run cookiesRemove="COOKIE_1,COOKIE_2"&gt;
		&lt;runName&gt;002_gotoDownload&lt;/runName&gt;
		&lt;url&gt;http://www.seleniumhq.org/download/&lt;/url&gt;
		&lt;successCondition&gt;
			&lt;element&gt;//h2&lt;/element&gt;
			&lt;elementContent&gt;Downloads&lt;/elementContent&gt;
		&lt;/successCondition&gt;
	&lt;/run&gt;
	&lt;run cookiesRemoveAll="true" fullscreen="true"&gt;
		&lt;runName&gt;003_search&lt;/runName&gt;
		&lt;inputFields&gt;
			&lt;inputField&gt;
				&lt;element&gt;//input[@name="q"]&lt;/element&gt;
				&lt;value&gt;webdriver&lt;/value&gt;
			&lt;/inputField&gt;
		&lt;/inputFields&gt;
		&lt;clickByXPATH&gt;//input[@value="Go"]&lt;/clickByXPATH&gt;
		&lt;successCondition elementExtractor="BY_ID" contentEvaluator="CONTAINS"&gt;
			&lt;element&gt;cse-footer&lt;/element&gt;
			&lt;elementContent&gt;Google&lt;/elementContent&gt;
		&lt;/successCondition&gt;
	&lt;/run&gt;
&lt;/plan&gt;
			</code>
		</pre>
		<ul>
			<li>&lt;cookiesRemoveAll&gt; is an **optional** attribute for plan and runs which removes all cookies</li>
			<li>&lt;cookiesRemove&gt; is an **optional** attribute for plan and runs which removes all passed cookies, comma separated values</li>
			<li>&lt;fullscreen&gt; is an **optional** attribute for plan and runs which maximize the window</li>
			<li>&lt;windowWidthPx&gt; is an **optional** attribute for runs where you put window width size in pixel. This attribute is not allowed if you are using fullscreen attribute in your plan</li>
			<li>&lt;windowHeightPx&gt; is an **optional** attribute for runs where you put window height size in pixel. This attribute is not allowed if you are using fullscreen attribute in your plan</li>
			<li>&lt;runName&gt; is a **require** node where you put the name (unique at all plan) and it used for screenshot naming</li>
			<li>&lt;url&gt; is an **optional** node where you put the "goToURL". **Remove this if it is not necessary for your run**</li>
			<li>&lt;inputFields&gt; is an **optional** list where you put your form filler. You must to use XPath for searching element and fill it with a value</li>
			<li>&lt;clickByXPATH&gt; is an **optional** node where you put your preferred action (click action)</li>
			<li>&lt;successCondition&gt; is a **require** node where you put the element selection and the value to be checked</li>
		</ul>
	</li>
</ol>

## Execute your plan/plans and enjoy it :)
You are ready to execute your plan, or plans.<br/>
Simple open your console (shell), point to your cloned repo (at pom.xml level) and call the command below:<br/>
<pre>
	<code>
mvn -f pom.xml compile exec:java -Dexec.mainClass=org.bitmarte.architecture.utils.testingframework.selenium.RunTestSuite -Dexec.args="/var/selenium/my-cfg"
	</code>
</pre>
**Pay attention: specify your base config path as the only require java argument ("/var/selenium/my-cfg" for this tutorial)**

<br/><br/><hr/>
## NTLM authentication
You can use NTLM authentication directly in your every runs which it need.
<pre>
	<code>
&lt;run&gt;
	&lt;runName&gt;001_ntlm&lt/runName&gt;
	...
	&lt;authentication authType="NTLM" waitPromptInSec="5"&gt;
		&lt;username>MY_USERNAME&lt;/username&gt;
		&lt;password>MY_PASSWORD&lt;/password&gt;
	&lt;/authentication&gt;
	...
	&lt;successCondition&gt;
		&lt;element&gt;//h2&lt;/element&gt;
		&lt;elementContent&gt;What is Selenium?&lt;/elementContent&gt;
	&lt;/successCondition&gt;
&lt;/run&gt;
	</code>
</pre>
Optionally you can use a custom attribute for setting up the NTLM prompt alert waiting time (waitPromptInSec):
<pre>
	<code>
&lt;run&gt;
	&lt;runName&gt;001_ntlm&lt/runName&gt;
	...
	&lt;authentication authType="NTLM" waitPromptInSec="5"&gt;
		&lt;username>MY_USERNAME&lt;/username&gt;
		&lt;password>MY_PASSWORD&lt;/password&gt;
	&lt;/authentication&gt;
	...
&lt;/run&gt;
	</code>
</pre>
The default value is 5 sec.
<br/><br/>
## Advanced success condition usage
You can use some advanced matchers at &lt;successCondition&gt; node in order to use make your test more powerful.

### screenshotFileName
You can specify a custom screenshot filename on success condition
Here you can find an example:

<pre>
	<code>
&lt;successCondition screenshotFileName="myScreenshotName"&gt;
	&lt;element&gt;//h5&lt;/element&gt;
	&lt;elementContent&gt;MyContent&lt;/elementContent&gt;
&lt;/successCondition&gt;
	</code>
</pre>

### waitBeforeScreenshotInMilliSec
You can specify a sleep time after success condition evaluator before take the screenshot.
Here you can find an example:

<pre>
	<code>
&lt;successCondition waitBeforeScreenshotInMilliSec="1000"&gt;
	&lt;element&gt;//h5&lt;/element&gt;
	&lt;elementContent&gt;MyContent&lt;/elementContent&gt;
&lt;/successCondition&gt;
	</code>
</pre>

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

<pre>
	<code>
&lt;successCondition contentEvaluator="REGEX"&gt;
	&lt;element&gt;//h5&lt;/element&gt;
	&lt;elementContent&gt;[0-9]&lt;/elementContent&gt;
&lt;/successCondition&gt;
	</code>
</pre>

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

<pre>
	<code>
&lt;successCondition contentEvaluator="REGEX" elementExtractor="BY_ID"&gt;
	&lt;element&gt;myName&lt;/element&gt;
	&lt;elementContent&gt;[0-9]&lt;/elementContent&gt;
&lt;/successCondition&gt;
	</code>
</pre>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: BY_XPATH**
<br/><br/>
## Advanced input field usage
You can use some advanced matchers at &lt;inputField&gt; node in order to use make your test more powerful.

### type
You can see the allowed values for this attribute:

| Attribute value        		| Description																|
| ----------------------------- | ------------------------------------------------------------------------- |
| TEXT							| For input type text field													|
| SELECT						| For select field (combo box), value based for setting						|
| RADIO							| For input type radio field												|

Here you can find an example that you can use for filling value "myValue" into an html element with "myName" ID value:

<pre>
	<code>
&lt;inputField type="SELECT"&gt;
	&lt;element&gt;myName&lt;/element&gt;
	&lt;value&gt;myValue&lt;/value&gt;
&lt;/inputField&gt;
	</code>
</pre>

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

<pre>
	<code>
&lt;inputField elementExtractor="BY_ID"&gt;
	&lt;element&gt;myName&lt;/element&gt;
	&lt;value&gt;myValue&lt;/value&gt;
&lt;/inputField&gt;
	</code>
</pre>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: BY_XPATH**
<br/><br/>
## Advanced run tags
There are some run tags (optionals) that you can use for advanced behaviors in your tests.

### BrowserAction
You can use &lt;browserAction&gt; tag for simulating action with top browser bar:

| Attribute value        		| Description																				|
| ----------------------------- | ----------------------------------------------------------------------------------------- |
| REFRESH						| Refresh current page (reload)																|
| BACK							| Go back to previous page																	|
| FORWARD						| Go ahead 																					|
| IFRAME_SWITCH					| Switch to iframe at a given XPath, the first one (elementByXPath attribute)				|

<pre>
	<code>
&lt;run&gt;
	&lt;runName&gt;001_refreshPage&lt/runName&gt;
	&lt;browserAction action="REFRESH"/&gt;
	&lt;successCondition&gt;
		&lt;element&gt;//h2&lt;/element&gt;
		&lt;elementContent&gt;What is Selenium?&lt;/elementContent&gt;
	&lt;/successCondition&gt;
&lt;/run&gt;
...
&lt;run&gt;
	&lt;runName&gt;001_refreshPage&lt/runName&gt;
	&lt;browserAction action="IFRAME_SWITCH" elementByXPath="//iframe"/&gt;
	&lt;successCondition&gt;
		&lt;element&gt;//h2&lt;/element&gt;
		&lt;elementContent&gt;What is Selenium?&lt;/elementContent&gt;
	&lt;/successCondition&gt;
&lt;/run&gt;
	</code>
</pre>

**Pay attention: &lt;browserAction&gt; tag is the first task that will be execute on your run, so you in some cases you need to separate your conceptual test run in more than one**
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

<pre>
	<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
...
&lt;webTimings/&gt;
...
&lt;/config&gt;
	</code>
</pre>

**Pay attention: WebTimings API can not be activate on Internet Explorer browser**

### Advanced WebTimings configuration
There are some advanced settings for KPIs performance monitoring
#### Timeout per KPI measure
The timeout if the KPI measures are always different each other.
<pre>
	<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
...
&lt;webTimings&gt;
	&lt;maxTimeoutPerMeasureInSec&gt;10&lt;/maxTimeoutPerMeasureInSec&gt;
&lt;/webTimings&gt;
...
&lt;/config&gt;
	</code>
</pre>
The default value is 5sec.
#### KPI interval measure
The time (in sec) between two KPI measures.
<pre>
	<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
...
&lt;webTimings&gt;
	&lt;kpiIntervalMeasureInSec&gt;1&lt;/kpiIntervalMeasureInSec&gt;
&lt;/webTimings&gt;
...
&lt;/config&gt;
	</code>
</pre>
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

<pre>
	<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
...
&lt;mobProxy&gt;
	&lt;chainedProxy&gt;my.host.proxy:9090&lt;/chainedProxy&gt;
	&lt;uploadBytePerSec&gt;1048576&lt;/uploadBytePerSec&gt;
	&lt;downloadBytePerSec&gt;7340032&lt;/downloadBytePerSec&gt;
&lt;/mobProxy&gt;
...
&lt;/config&gt;
	</code>
</pre>

### Generating HAR file
You can produce an HAR file (one file for each page) with a simple additional attribute, boolean value.

<pre>
	<code>
&lt;config&gt;
	&lt;browserMode&gt;REMOTE&lt;/browserMode&gt;
	&lt;browserName&gt;FIREFOX&lt;/browserName&gt;
...
&lt;mobProxy enableHarCapture="true"/&gt;
...
&lt;/config&gt;
	</code>
</pre>