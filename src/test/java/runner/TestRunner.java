package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps"},
        tags = "@all",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        publish = true
)
public class TestRunner {
}
