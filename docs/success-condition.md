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
	
### maxTimeOutPerSuccessConditionInSec
You can override this property directly in your successCondition tag for each run.

	<successCondition maxTimeOutPerSuccessConditionInSec="30">
		<element>//h5</element>
		<elementContent>MyContent</elementContent>
	</successCondition>

* [Evaluator](evaluator.md)
* [Extractor](extractor.md)