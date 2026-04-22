package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.AllureReportManager;
import utils.RetryAnalyzer;

@Test(retryAnalyzer = RetryAnalyzer.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
        		 "pretty",                                 // Console output with formatting
        	        "html:target/cucumber-html-report.html",  // HTML report
        	        "json:target/cucumber-report.json",       // JSON report
        	        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Allure integration
        },
        monochrome = true
)
@Listeners(utils.ExtentReportManager.class)
public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite
	public void beforeSuite() {
		AllureReportManager.cleanAllureResults();
	}
	@AfterSuite
	public void afterSuite() {
		AllureReportManager.openAllureReport();
	}
}
