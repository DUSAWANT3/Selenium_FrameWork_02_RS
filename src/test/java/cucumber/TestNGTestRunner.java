package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/cucumber",      // Path to your feature files
        glue = "org.dutesting.stepDefinitions",   // Package containing step definitions class
        monochrome = true,                         // Cleaner console output
        plugin = {"html:target/cucumber.html"}      // Report generation
)
       //tags = "@ErrorValidation"                 // Uncomment to run specific tag
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    // Extending AbstractTestNGCucumberTests integrates Cucumber with TestNG
    //new comment
}
