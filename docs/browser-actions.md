### elementExtractor
You can see the allowed values for this attribute


| Attribute value        		| Description																
| ----------------------------- | ------------------------------------------------------------------------- 
| BY_ID							| Using HTML "id" attribute for finding element								
| BY_XPATH						| Using XPath for finding element, all matched elements will be returned	
| BY_CLASSNAME					| Using HTML "class" attribute for finding element							

Here you can find an example that you can use for filling value "myValue" into an html element with "myName" ID value


	<inputField elementExtractor="BY_ID">
		<element>myName</element>
		<value>myValue</value>
	</inputField>

**Pay attention: when attribute is not present or an unknown value is used, the dafault content evaluator will be use: BY_XPATH*