package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
        "json:target/reports/cucumber-json-reports/json-report.json",
        "html:target/reports/cucumber-html-reports/html-report.html"
        },
        features = {"src/test/java/features" },
        glue = {"steps", "com.cucumber.pageobjectmodel"}
)

public class CucumberRunnerTest {
}
