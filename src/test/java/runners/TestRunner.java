package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
//import utility.DriverManager;


@CucumberOptions(
        tags= "",
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}//,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    WebDriver driver;

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }



}
