package commonlib


import java.util.concurrent.TimeUnit

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.support.events.EventFiringWebDriver



class GroovyUtils
{

	static FirefoxOptions getFirefoxOptions()
	{
		def options = new FirefoxOptions()
		return options
	}
	
	//*************************************************************************************************
	
	static ChromeOptions getChromeOptions()
	{
		def options = new ChromeOptions()
		return options
	}
	
	//*************************************************************************************************
	
	static InternetExplorerOptions getIEOptions()
	{
		def options = new InternetExplorerOptions()
		return options
	}
	
	
	//*************************************************************************************************
	
	static int getBrowserId(String sBrowserName)
	{
		
		if (sBrowserName.equalsIgnoreCase("chrome")) return 1
		
		if (sBrowserName.equalsIgnoreCase("firefox")) return 2
		
		if (sBrowserName.equalsIgnoreCase("ie")) return 3
		
		return -1
	}
	
	//*************************************************************************************************
	
	static WebDriver getBrowser(String sBrowserName)
	{
		WebDriver oDriver;
		
		switch(getBrowserId(sBrowserName))
		{
			case 1:
				System.setProperty("webdriver.chrome.driver", GlobalConstants.sChromeDriverPath)
				oDriver = new ChromeDriver(getChromeOptions())
				break
			
			case 2:
				System.setProperty("webdriver.gecko.driver", GlobalConstants.sGeckoDriverPath)
				oDriver = new FirefoxDriver(getFirefoxOptions())
				break
			
			case 3:
				System.setProperty("webdriver.ie.driver", GlobalConstants.sIEDriverPath)
				oDriver = new InternetExplorerDriver(getIEOptions())
				break
				
			default:
				throw new Exception("Unknown browser: " + sBrowserName)
		}
		
		oDriver.manage().window().maximize()
		oDriver.manage().deleteAllCookies()
		
		oDriver.manage().timeouts().pageLoadTimeout(GlobalConstants.lngPageLoadTimeout, TimeUnit.SECONDS)
							
		oDriver.manage().timeouts().implicitlyWait(GlobalConstants.lngImplicitWaitTimeout, TimeUnit.SECONDS)

		return oDriver

	}

}

