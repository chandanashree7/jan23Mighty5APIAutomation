package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/getbatchbyprogramId.feature" }, // location of feature files
		glue =  "stepDefinition", 
		dryRun=	false,
		plugin = { "pretty", "html:target/cucumber.html" }, 
		monochrome = true)

public class Runner {

}
