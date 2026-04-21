package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage {
	WebDriver driver;

	// 1. Registration pop-up dismiss
	@FindBy(xpath = "//button[@aria-label='Dismiss sign-in info.']")
	private WebElement dismissPopup;

	// 2. Sign-in button
	@FindBy(xpath = "//a[@data-testid='header-sign-in-button']")
	private WebElement signInButton;

	// 3. Google sign-in option
	@FindBy(xpath = "//a[@aria-label='Sign in with Google']")
	private WebElement googleSignIn;

	// 4. Email field in Google login
	@FindBy(id = "identifierId")
	private WebElement emailField;

	// 5. Next button in Google login
	@FindBy(xpath = "//*[@id='identifierNext']/div/button")
	private WebElement nextButton;

	// 6. Error message
	@FindBy(xpath = "//div[@id='i8']/div")
	private WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void closePopup() {
		dismissPopup.click();
	}

	public void clickSignIn() {
		signInButton.click();
	}

	public void clickGoogleSignIn() {
		googleSignIn.click();
	}

	public void switchToGoogleWindow() {
		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(allWindows);
		driver.switchTo().window(windowList.get(1));
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void clickNext() {
		nextButton.click();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}
}
