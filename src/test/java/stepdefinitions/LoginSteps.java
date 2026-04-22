package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage;

    @Given("I am on Booking.com homepage for login")
    public void open_homepage() {
//        DriverManager.getDriver().get("https://booking.com/");
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("I try to login with invalid email {string}")
    public void login_invalid(String email) {
        loginPage.closePopup();
        loginPage.clickSignIn();
        loginPage.clickGoogleSignIn();
        loginPage.switchToGoogleWindow();

        loginPage.enterEmail(email);
        loginPage.clickNext();
    }

    @Then("Error message should be displayed")
    public void verify_error() {
    	System.out.println("------------------------------------------------------------------");
        System.out.println("Error message: " + loginPage.getErrorMessage());
        System.out.println("------------------------------------------------------------------");
    }
}
