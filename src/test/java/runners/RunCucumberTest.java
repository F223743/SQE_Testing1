package runners; // Replace with your actual package name

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test Runner for Cucumber Tests
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        "E:\\SQE project\\selenium_mvn_eclipse\\src\\test\\resources\\feature\\login.feature",
        "E:\\SQE project\\selenium_mvn_eclipse\\src\\test\\resources\\feature\\Product.feature"  // Added path to product.feature
    },
    glue = {"selenium_mvn_eclipse"}, // Package containing step definitions
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber.json",
        "junit:target/cucumber.xml"
    },
    monochrome = true
)
public class RunCucumberTest {
}
