## BrowserMob Proxy (bandwidth throttling and more
You can use BrowserMob-Proxy (https://github.com/lightbody/browsermob-proxy) in order to introduce a light-proxy in your tests
So you will have some features as configuration (config.xml), here in the table below (see BrowserMobProxy interface on github project)

| FEATURE	        		| Required (default)	| Description																								
| ------------------------- | ---------------------	|---------------------------------------------------------------------------------------------------------- 
| chainedProxy				|	NO (no proxy)		| Sets an upstream proxy that this proxy will use to connect to external hosts (eg. hostname:port)			
| port						|	NO (0)				| Starts the proxy on the specified port																	
| downloadBytePerSec		|	NO (no limit)		| The time spent for the requests (msec) [responseStart - requestStart]										
| uploadBytePerSec			| 	NO (no limit)		| The time spent for retrieving the resources (msec) [responseEnd - responseStart]							
| latencyInMillisec			| 	NO (no limit)		| Network latency																							

	<config>
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
		...
		<mobProxy>
			<chainedProxy>my.host.proxy:9090</chainedProxy>
			<uploadBytePerSec>1048576</uploadBytePerSec>
			<downloadBytePerSec>7340032</downloadBytePerSec>
		</mobProxy>
		...
	</config>

### Generating HAR file
You can produce an HAR file (one file for each page) with a simple additional attribute, boolean value

	<config
		<browserMode>REMOTE</browserMode>
		<browserName>FIREFOX</browserName>
		..
		<mobProxy enableHarCapture="true"/>
		..
	</config>