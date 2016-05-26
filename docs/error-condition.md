## Advanced error condition usage
You can put your errorConditions for each run in order to override that you had specify into config file.

	<run>
		...
		<errorConditions>
			<errorCondition elementExtractor="BY_CLASSNAME" contentEvaluator="CONTAINS">
				<element>error-msg-container</element>
				<elementContent>error-msg-content</elementContent>
			</errorCondition>
		</errorConditions>
	</run>
	
### maxTimeOutPerErrorConditionInSec
You can override this property directly in your errorCondition tag for each run.

	<run>
		...
		<errorConditions>
			<errorCondition maxTimeOutPerErrorConditionInSec="4">
				<element>error-msg-container</element>
				<elementContent>error-msg-content</elementContent>
			</errorCondition>
		</errorConditions>
	</run>

* [Evaluator](evaluator.md)
* [Extractor](extractor.md)