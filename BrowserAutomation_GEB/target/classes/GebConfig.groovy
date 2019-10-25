//------------ this file must be kept at default package....
import org.openqa.selenium.chrome.ChromeDriver
import commonlib.GroovyUtils

//Wait timeout is in seconds which will be used by waitFor

waiting {
	timeout = 10
	retryInterval = 1.0
}

baseUrl = "https://www.anz.com.au/"

//------- adding default browser as chrome browser

driver = {  GroovyUtils.getBrowser("chrome") }


reportsDir = "target/geb-reports"
atCheckWaiting = true
baseNavigatorWaiting = true
