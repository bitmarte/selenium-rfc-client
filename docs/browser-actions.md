# BrowserActions
Here you can find the lists of allowed actions:
<hr/>
*[BackAction](docs/browser-actions.md#BackAction)
*[ClickAction](docs/browser-actions.md#ClickAction)
*[ComboFillAction](docs/browser-actions.md#ComboFillAction)
*[ForwardAction](docs/browser-actions.md#ForwardAction)
*[GoToUrlAction](docs/browser-actions.md#GoToUrlAction)
*[IFrameSwitchAction](docs/browser-actions.md#IFrameSwitchAction)
*[InputFillAction](docs/browser-actions.md#InputFillAction)
*[RadioFillAction](docs/browser-actions.md#RadioFillAction)
*[RefreshAction](docs/browser-actions.md#RefreshAction)
*[RemoveAllCookiesAction](docs/browser-actions.md#RemoveAllCookiesAction)
*[RemoveCookiesAction](docs/browser-actions.md#RemoveCookiesAction)
*[WindowResizeAction](docs/browser-actions.md#WindowResizeAction)
<hr/>

## BackAction
It simulates the back button of the browser:

	<back waitBeforeActionInMillis="200"/>

'waitBeforeActionInMillis' is an optiona attribute where you can put your waiting time before each action.

## ClickAction
It simulates the click:

	<click>
		<element>//input[@value="Go"]</element>
	</click>

For advanced element usage see below here [Extractor](docs/extractor.md).

## ComboFillAction
It simulates the combo selection:

	<comboFill>
		<element>//select[@value="myCombo"]</element>
		<value>1</value>
	</comboFill>

The 'value' node must contain the option value of your combo box.
For advanced element usage see below here [Extractor](docs/extractor.md).

## ForwardAction
It simulates the forward button of the browser:

	<forward/>

## GoToUrlAction
It simulates the address bar filling of the browser:

	<goTo>
		<url>http://www.seleniumhq.org/</url>
	</goTo>

## IFrameSwitchAction
It allows you to select an iframe in the page:

	<iFrameSwitch>
		<element>//iframe[@value="myFrame"]</element>
	</iFrameSwitch>

For advanced element usage see below here [Extractor](docs/extractor.md).

## InputFillAction
It simulates the input text filling:

	<inputFill>
		<element>//input[@name="q"]</element>
		<value>webdriver</value>
	</inputFill>

For advanced element usage see below here [Extractor](docs/extractor.md).

## RadioFillAction
It simulates the radio selection:

	<radioFill>
		<element>//radio[@name="myRadio"]</element>
		<value>text</value>
	</radioFill>

The 'value' node must contain the value of the radio.
For advanced element usage see below here [Extractor](docs/extractor.md).

## RefreshAction
It simulates the refresh button of the browser:

	<refresh/>

## RemoveAllCookiesAction
It removes all cookies:

	<removeAllCookies/>

## RemoveCookiesAction
It removes the specified cookies:

	<removeCookies>
		<cookiesName>cookieName_1</cookiesName>
		<cookiesName>cookieName_2</cookiesName>
		<cookiesName>cookieName_2</cookiesName>
	</removeCookies>

## WindowResizeAction
It resizes your window.
Full screen:

	<windowResize>
		<fullScreen>true</fullScreen>
	</windowResize>

Your size:
	
	<windowResize>
		<widthPx>1440</widthPx>
		<heightPx>900</heightPx>
	</windowResize>