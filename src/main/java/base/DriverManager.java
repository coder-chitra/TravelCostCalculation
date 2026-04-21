package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import utils.ConfigReader;

/**
 * Utility class for initializing and managing WebDriver instances.
 */
public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    @SuppressWarnings("deprecation")
    public static WebDriver initializeDriver(String browser) throws MalformedURLException {
        String executionEnv = ConfigReader.getProperty("environment").toLowerCase();
        String browserType = Reporter.getCurrentTestResult()
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter("browser");
        String os = ConfigReader.getProperty("os").toLowerCase();

        if (executionEnv.equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS); break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC); break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX); break;
                default:
                    throw new IllegalArgumentException("Unsupported OS: " + os);
            }

            switch (browserType) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.merge(capabilities);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-notifications");
                    edgeOptions.merge(capabilities);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browserType: " + browserType);
            }

        } else if (executionEnv.equals("local")) {
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        String url = ConfigReader.getProperty("app.url");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();

        return driver;
    }

    public static void driverTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
