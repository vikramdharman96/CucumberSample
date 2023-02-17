package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue= {"stepdefinitions","hooks"},	
		dryRun=false,
		monochrome=true,
		publish=true,
		plugin={"pretty",
				"html:target/CucumberReports/CucumberReports.html",
				"json:target/CucumberReports/CucumberReports.json",
				"junit:target/CucumberReports/CucumberReports.xml"}
					)
public class TestRunner {
	 
	

}
