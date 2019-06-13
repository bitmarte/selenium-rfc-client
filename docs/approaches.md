## Two different approaches (local vs. remote)
It depends on your goal you can run a test on two different platforms: "local" or "remote".<br/>
**Don't worry, you can run the same test on both platforms.**<br/>

### Local approach
In this case the client execute tests on the same machine that you use to run it.<br/>
You need to be sure that the browser used for testing is installed on you machine, also the right web-driver (it depends on the browser):
<ul>
	<li>
		Google Chrome: https://sites.google.com/a/chromium.org/chromedriver/downloads
	</li>
	<li>
		Mozilla Firefox: https://github.com/mozilla/geckodriver/releases
	</li>
</ul>
**Chrome headless mode is allowed only for LOCAL mode**

### Remote approach
In this case the client execute tests on remote machine.<br/>
**You need to be sure that Selenium Standalone Server (http://docs.seleniumhq.org/download/) and the browser used for testing are installed on remote machine**.