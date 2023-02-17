package hooks;


import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

public class MyHooks {
	
	WebDriver driver; 


	 
	@Before
	public void setup() {
		
		Properties prop =new ConfigReader().initializeProperties();
//		 Properties prop = configReader.initializeProperties();
		 driver = DriverFactory.initialiseBrowser(prop.getProperty("browser"));
		 driver.get(prop.getProperty("url"));
	}
	@After
	public void teardown(Scenario scenario) {
		String scenarioName = scenario.getName().replaceAll(" ","_");
		if (scenario.isFailed()) {
			byte[] screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotAs,"image/png",scenarioName);
		}
		driver.quit();

	}

}
