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