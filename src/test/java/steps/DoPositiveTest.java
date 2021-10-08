package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SignInPage;
import setups.Capabilities;

import java.net.MalformedURLException;

public class DoPositiveTest extends Capabilities {
    static SignInPage signInPage;

    @Before
    public void setup() throws Exception {
        setupCapabilities();
    }


    @Given("^user launch the app$")
    public void userLaunchTheApp() throws MalformedURLException, InterruptedException {
        launchApp();
        signInPage = new SignInPage(androidDriver);
    }

    @And("^user taps on country code$")
    public void userSelectCountryCode() throws Throwable {
        signInPage.ClickCountryCode();
    }


    @When("^user search for country or code \"([^\"]*)\"$")
    public void userSearchForCountryOrCode(String search) throws Throwable {
        signInPage.seacrCountryCode(search);
    }

    @Then("^country \"([^\"]*)\" with code \"([^\"]*)\" is displayed in search result$")
    public void countryWithCodeIsDisplayedInSearchResult(String countryName, String code) throws Throwable {
        signInPage.verifyTextIsDisplayedOnScreen(countryName);
        signInPage.verifyTextIsDisplayedOnScreen(code);
    }

    @When("^user taps \"([^\"]*)\"$")
    public void userSelects(String text) throws Throwable {
        signInPage.tapElementWithText(text);
    }

    @Then("^user see \"([^\"]*)\" on screen$")
    public void userSeeOnScreen(String text) {
        signInPage.verifyTextIsDisplayedOnScreen(text);
    }

    @Then("^user is on the countries listing$")
    public void userIsOnTheJobsListing() {
        signInPage.verifyTextIsDisplayedOnScreen("Albania");
    }

    @And("^user enters phone number \"([^\"]*)\"$")
    public void userEntersPhoneNumber(String phoneNum) throws Throwable {
        signInPage.enterPhoneNumber(phoneNum);
    }

    @When("^user enter sms code \"([^\"]*)\"$")
    public void userEnterSmsCode(String smsCode) throws Throwable {
        SignInPage signInPage = new SignInPage(androidDriver);
        signInPage.EnterCode(smsCode);
    }

    @Then("^user verify the success message \"([^\"]*)\"$")
    public void userVerifyTheSuccessMessage(String msg) throws Exception {
        signInPage.verifySuccessMessage(msg);
    }

    @Then("user do not see {string}")
    public void userDoNotSee(String text) throws InterruptedException {
        signInPage.verifyTextIsNotDisplayedOnScreen(text);
    }

    @After()
    public void teardown() throws InterruptedException {
        closeDriver();
    }
}