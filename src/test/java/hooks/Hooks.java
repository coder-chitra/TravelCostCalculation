package hooks;

import base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;
import utils.Screenshot;

import java.net.MalformedURLException;

/**
 * Hooks class to manage WebDriver lifecycle for Cucumber scenarios.
 */
public class Hooks {

    @Before
    public void setUp() throws MalformedURLException {
        // Initialize driver before each scenario
        String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
        DriverManager.initializeDriver(browser);

        // Navigate to base URL from config.properties
        String url = ConfigReader.getProperty("app.url");
        DriverManager.getDriver().get(url);
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        try {
            // Take screenshot after each scenario
            String screenshotPath = Screenshot.screenShotTC(
                    DriverManager.getDriver(),
                    scenario.getName().replaceAll(" ", "_")
            );
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        } finally {
            DriverManager.quitDriver();
        }
    }
}
