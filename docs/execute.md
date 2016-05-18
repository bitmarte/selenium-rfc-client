## Execute your plan/plans and enjoy it :)
You are ready to execute your plan, or plans.<br/>
Simple open your console (shell), point to your cloned repo (at pom.xml level) and call the command below:<br/>

	mvn -f pom.xml compile exec:java -Dexec.mainClass=org.bitmarte.architecture.utils.testingframework.selenium.RunTestSuite -Dexec.args="/var/selenium/my-cfg"
	
**Pay attention: specify your base config path as the only require java argument ("/var/selenium/my-cfg" for this tutorial)**