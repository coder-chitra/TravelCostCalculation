package pages;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
 
 
public class CruisePage {
	WebDriver driver;
	WebDriverWait wait;
	// 1. Registration pop-up dismiss
	@FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
	private WebElement dismissPopup;
 
	// 2. Attractions link
	@FindBy(id = "attractions")
	private WebElement attractionsLink;
 
	// 3. Destination input
	@FindBy(xpath = "//input[@data-testid='search-input-field']")
	private WebElement searchInput;
 
	// 4. London location option
	@FindBy(xpath = "//div[text()='London, Greater London']")
	private WebElement londonOption;
 
	// Search button
	@FindBy(xpath = "//button[@data-testid='search-button']")
	private WebElement searchButton;
 
	// Sidebar arrow button
	@FindBy(xpath = "//span[text()='Tours ']/ancestor::span/following-sibling::button")
	private WebElement arrowButton;
 
	// Boat tours & cruises filter
	@FindBy(xpath = "//span[text()='Boat tours & cruises']")
	private WebElement cruiseFilter;
 
	// Cruise cards
	@FindBy(xpath = "//div[@data-testid='card']/descendant::a[contains(text(),'Cruise')]")
	private List<WebElement> cruises;
 
	// Cruise prices
	@FindBy(xpath = "//div[@data-testid='card']/descendant::a[contains(text(),'Cruise')]/ancestor::div[@class='css-2kol2l']/following-sibling::div/descendant::div[@data-testid='price']/descendant::div[@class='e7addce19e css-g82t4m']")
	private List<WebElement> prices;
 
	public CruisePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
 
	// Actions
	public void closePopup() {
		dismissPopup.click();
	}
 
	public void clickAttractions() {
		attractionsLink.click();
	}
 
	public void enterDestination(String city) {
		searchInput.click();
		searchInput.sendKeys(city);
	}
 
	public void selectLondonOption() {
		wait.until(ExpectedConditions.visibilityOf(londonOption)).click();
	}
 
	public void selectCheckInDate(LocalDate date) {
		String xpath = String.format("//span[@data-date='%s']", date);
		driver.findElement(By.xpath(xpath)).click();
	}
 
	public void selectCheckOutDate(LocalDate date) {
		String xpath = String.format("//span[@data-date='%s']", date);
		driver.findElement(By.xpath(xpath)).click();
	}
 
	public void clickSearch() {
		searchButton.click();
	}
 
	public void expandCruiseFilter(JavascriptExecutor js) {
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", arrowButton);
		wait.until(ExpectedConditions.visibilityOf(arrowButton)).click();
		wait.until(ExpectedConditions.visibilityOf(cruiseFilter)).click();
	}
 
	public void printCruiseDetails(JavascriptExecutor js) {
		
		int top = 3;
		String currentWindow = driver.getWindowHandle();
 
		for (int i = 0; i < cruises.size() && top > 0; i++, top--) {
			System.out.println(
					"\n------------------------------------------------------------------------------------------------------------------");

			wait.until(ExpectedConditions.visibilityOf(cruises.get(i)));
			String title = cruises.get(i).getText();
			System.out.println((i + 1) + ". " + title);
 
			try {
				wait.until(ExpectedConditions.visibilityOf(prices.get(i)));
				String price = prices.get(i).getText();
				System.out.println("Price : " + price);
			} catch (Exception e) {
				System.out.println("Price not given");
			}
 
			cruises.get(i).click();
			Set<String> allWindows = driver.getWindowHandles();
 
			for (String w : allWindows) {
				if (!w.equals(currentWindow)) {
					driver.switchTo().window(w);
					try {
						String duration = driver
								.findElement(By.xpath("//div[@class='e7addce19e' and contains(text(),'Duration')]"))
								.getText();
						System.out.println(duration);
					} catch (Exception e) {
						System.out.println("Duration not given");
					}
 
					List<WebElement> languages = driver.findElements(By.xpath(
							"//h3[text()='Audio guide available in multiple languages']/following-sibling::div/descendant::div[@class='a9918d47bf']"));
					if (languages.isEmpty()) {
						System.out.println("No Languages Offered!");
					} else {
						System.out.print("Languages Offered : ");
						for (WebElement lang : languages) {
							System.out.print(lang.getText() + " ");
						}
					}
					driver.close();
					driver.switchTo().window(currentWindow);
					break;
				}
			}
		}
		System.out.println(
				"\n------------------------------------------------------------------------------------------------------------------");
	}
}