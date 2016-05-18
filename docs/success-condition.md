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