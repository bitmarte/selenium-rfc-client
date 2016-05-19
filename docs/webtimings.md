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

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings/>
	...
	</config>

When you activate 'webTimings' a CSV report will be created on your 'reportBaseDir' folder, called 'web-timings.csv'.

**Pay attention: WebTimings API can not be activate on Internet Explorer browser**

There are some advanced settings for KPIs performance monitoring
### Timeout per KPI measure
The timeout if the KPI measures are always different each other.

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings>
		<maxTimeoutPerMeasureInSec>10</maxTimeoutPerMeasureInSec>
	</webTimings>
	...
	</config>

The default value is 5sec.
### KPI interval measure
The time (in sec) between two KPI measures.

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
	...
	<webTimings>
		<kpiIntervalMeasureInSec>1</kpiIntervalMeasureInSec>
	</webTimings>
	...
	</config>

The default value is 0sec, no delay.