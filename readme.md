# <img src="./docs/images/icon.png" width="40"/> Selenium Run-From-Configuration client
This project allow you to create and run ent-to-end tests, running on real browser.<br/>
The artifact is a simple JAR file that you need to run directly from a shell; it requires only one argument, the path where you put configuration files (setup and plans).

<hr/>
* [Requirements](docs/requirements.md)
* [Two different approaches (local vs. remote)](docs/approaches.md)
 * [Local approach](docs/approaches.md#local-approach)
 * [Remote approach](docs/approaches.md#local-approach)
* [Client setup](docs/setup.md)
* [Make your plan](docs/make-your-plan.md)
* [Execute your plan/plans and enjoy it :)](docs/execute.md)
* [NTLM authentication](docs/ntlm-auth.md)
* [Browser actions usage](docs/browser-actions.md)
* [Advanced success condition usage](docs/success-condition.md)
 * [screenshotFileName](docs/success-condition.md#screenshotfilename)
 * [waitBeforeScreenshotInMilliSec](docs/success-condition.md#waitbeforescreenshotinmillisec)
 * [contentEvaluator](docs/success-condition.md#contentevaluator)
 * [elementExtractor](docs/success-condition.md#elementextractor)
* [Advanced error condition usage](docs/error-condition.md)
 * [contentEvaluator](docs/error-condition.md#contentevaluator)
 * [elementExtractor](docs/error-condition.md#elementextractor)
* [WebTimings performance monitoring](docs/webtimings.md)
 * [Timeout per KPI measure](docs/webtimings.md#kpi-interval-measure)*
 * [KPI interval measure](docs/webtimings.md#kpi-interval-measure)
* [BrowserMob Proxy (for bandwidth throttling and more)](docs/browsermob.md)
 * [Generating HAR file](docs/browsermob.md#generating-har-file)
* [Concurrent plan](docs/concurrent-plan.md)
<hr/>