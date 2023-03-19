package cucumber.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/TestByFeatureFileBDD",
        glue={
                "cucumber.stepdefs"
        }
)

public class CucumberTestRunner {
}
