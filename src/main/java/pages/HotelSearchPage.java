package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.List;

public class HotelSearchPage {
	WebDriver driver;

	// 1. Registration pop-up dismiss
	@FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
	private WebElement dismissPopup;

	// 2. Destination input container
	@FindBy(xpath = "//div[@data-testid='destination-container']")
	private WebElement destinationContainer;

	// 3. Destination text box
	@FindBy(id = ":R55amr5:")
	private WebElement destinationTextBox;

	// 4. Nairobi location option
	@FindBy(xpath = "//div[text()='Nairobi']/following-sibling::div[text()='Kericho, Kericho, Kenya']")
	private WebElement nairobiOption;

	// 9. Persons selector
	@FindBy(xpath = "//span[@data-testid='searchbox-form-button-icon']")
	private WebElement personsSelector;

	// 10. Adults + button
	@FindBy(xpath = "//div[@class='c5aae0350e']/label[@for='group_adults']/parent::div/following-sibling::div/button[2]")
	private WebElement adultsPlusButton;

	// Search button
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButton;

	// Sort dropdown
	@FindBy(xpath = "//button[@data-testid='sorters-dropdown-trigger']")
	private WebElement sortDropdown;

	// Sort by top-reviewed
	@FindBy(xpath = "//button[@aria-label='Top reviewed']")
	private WebElement sortTopReviewed;

	// Expand facilities filter
	@FindBy(xpath = "(//span[@data-testid='filters-group-expand'])[1]")
	private WebElement expandFacilities;

	// Free Wifi option
	@FindBy(xpath = "//div[text()='Free Wifi']")
	private WebElement freeWifiOption;

	// Hotel results
	@FindBy(xpath = "//div[@data-testid='title']")
	private List<WebElement> hotelNames;

	@FindBy(xpath = "//button[@data-testid='distance']")
	private List<WebElement> distances;

	@FindBy(xpath = "//span[@data-testid='price-and-discounted-price']")
	private List<WebElement> prices;

	public HotelSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void closePopup() {
		dismissPopup.click();
	}

	public void enterDestination(String city) {
		destinationContainer.click();
		destinationTextBox.sendKeys(city);
	}

	public void selectNairobiOption() {
		nairobiOption.click();
	}

	public void selectCheckInDate(LocalDate date) {
		String xpath = String.format("//span[@data-date='%s']", date);
		driver.findElement(By.xpath(xpath)).click();
	}

	public void selectCheckOutDate(LocalDate date) {
		String xpath = String.format("//span[@data-date='%s']", date);
		driver.findElement(By.xpath(xpath)).click();
	}

	public void openPersonsSelector() {
		personsSelector.click();
	}

	public void setAdults(int count) {
		// Default is 2 adults, so click until desired count
		for (int i = 2; i < count; i++) {
			adultsPlusButton.click();
		}
	}

	public void clickSearch() {
		searchButton.click();
	}

	public void sortByTopReviewed() {
		sortDropdown.click();
		sortTopReviewed.click();
	}

	public void filterFreeWifi() {
		expandFacilities.click();
		freeWifiOption.click();
	}

	public void printHotelResults() {
		System.out.println("--------------------------------------------------");
		System.out.println("  Hotel Name \tDistance From Downtown\tPrice");
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < hotelNames.size(); i++) {
			String hotelName = hotelNames.get(i).getText();
			String distance = distances.get(i).getText();
			String price = prices.get(i).getText();
			String shortName = hotelName.length() > 10 ? hotelName.substring(0, 10) : hotelName;
			System.out.println((i + 1) + ". " + shortName + "\t" + distance + "\t" + price);
		}
	}
}
