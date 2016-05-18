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