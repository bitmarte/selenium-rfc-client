## elementExtractor
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

## maxTimeOutPerElementExtratorInSec
This is an **optional** attribute for setting the timeout (in second) used for element extractors overriding the configuration.
Here you can find an example, waiting until 20sec:

	<successCondition contentEvaluator="REGEX" elementExtractor="BY_ID" maxTimeOutPerElementExtratorInSec="20">
		<element>myName</element>
		<elementContent>[0-9]</elementContent>
	</successCondition>

**Pay attention: take a look at [Setup](setup.md) for the default value**