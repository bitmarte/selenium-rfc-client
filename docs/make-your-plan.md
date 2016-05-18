## Make your plan
<ol>
	<li>Open "sample-plan.xml" or create new one (eg. /var/selenium/my-cfg/plans/sample-plan.xml)</li>
	<li>
		Let's create your first run! A run is an action (eg. click or form filling) with a success condition.
		You can find a run example below:
	</li>
</ol>	

	<plan>
		<run>
			<runName>001_gotoSeleniumHQ</runName>
			<browserActions>
				<windowResize>	
					<widthPx>1440</widthPx>
					<heightPx>900</heightPx>
				</windowResize>
				<goTo>
					<url>http://www.seleniumhq.org/</url>
				</goTo>
			</browserActions>
			<successCondition contentEvaluator="CONTAINS">
				<element>//h2</element>
				<elementContent>What is Selenium</elementContent>
			</successCondition>
		</run>
		<run>
			<runName>002_gotoDownload</runName>
			<browserActions>
				<goTo>
					<url>http://www.seleniumhq.org/download/</url>
				</goTo>
			</browserActions>
			<successCondition>
				<element>//h2</element>
				<elementContent>Downloads</elementContent>
			</successCondition>
		</run>
		<run>
			<runName>003_search</runName>
			<browserActions>
				<inputFill>
					<element>//input[@name="q"]</element>
					<value>webdriver</value>
				</inputFill>
				<click>
					<element>//input[@value="Go"]</element>
				</click>
			</browserActions>
			<successCondition elementExtractor="BY_ID" contentEvaluator="CONTAINS">
				<element>cse-footer</element>
				<elementContent>Google</elementContent>
			</successCondition>
		</run>
	</plan>
			
<ul>
	<li>'runName' is a **require** node where you put the name (unique at all plan) and it used for screenshot naming</li>
	<li>'browserActions' is a **require** node where you put your actions</li>
	<li>'successCondition' is a **require** node where you put the element selection and the value to be checked</li>
</ul>