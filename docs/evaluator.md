## contentEvaluator
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