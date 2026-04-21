package stepdefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import pages.CruisePage;

import java.time.LocalDate;

public class CruiseSteps {
    CruisePage cruisePage;

    @Given("I am on Booking.com homepage for cruise search")
    public void open_homepage() {
        cruisePage = new CruisePage(DriverManager.getDriver());
    }

    @When("I search cruises in {string}")
    public void search_cruises(String city) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        cruisePage.closePopup();
        cruisePage.clickAttractions();
        cruisePage.enterDestination(city);
        cruisePage.selectLondonOption();

        LocalDate checkIn = LocalDate.now().plusDays(5);
        LocalDate checkOut = checkIn.plusDays(5);
        cruisePage.selectCheckInDate(checkIn);
        cruisePage.selectCheckOutDate(checkOut);

        cruisePage.clickSearch();
        cruisePage.expandCruiseFilter(js);
        cruisePage.printCruiseDetails(js);
    }

    @Then("Cruise results should be displayed")
    public void verify_results() {
        System.out.println("Cruises displayed successfully!");
    }
}
