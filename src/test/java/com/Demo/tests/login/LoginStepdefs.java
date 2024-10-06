package com.Demo.tests.login;

import com.Demo.base_test.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class LoginStepdefs extends BaseTest {


    @When("I open the side menu")
    public void iOpenTheSideMenu() {

        device.demoSauceApp.login.openSideMenu();
    }

    @When("I click the Log In button")
    public void iClickTheLogInButton() {
        device.demoSauceApp.login.clickLoginButton();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        System.out.println("user name " + username + "" + users.getProperty(username));
        device.demoSauceApp.login.enterUsername(users.getProperty(username));
        device.demoSauceApp.login.enterPassword(users.getProperty(password));
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        device.demoSauceApp.login.clickLogin();
        device.demoSauceApp.waitForLoadingToFinish();
    }

    @Then("I Should be navigated to checkout page")
    public void iShouldSeeAWelcomeMessage() {
        assertTrue(device.demoSauceApp.login.isLoginSuccessful());
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        assertTrue(device.demoSauceApp.login.getWrongErrorMessage().isDisplayed());
    }

    @And("I click the Log out button")
    public void iClickTheLogOutButton() throws InterruptedException {
        device.demoSauceApp.login.clickLogoutButton();
    }

    @Given("I launch the app")
    public void iLaunchTheApp() {
    }

    @And("I confirm logout")
    public void iConfirmLogout() {
        device.demoSauceApp.login.clickLogout();

    }

    @Then("I should locked see an error message")
    public void iShouldLockedSeeAnErrorMessage() {
        assertTrue(device.demoSauceApp.login.getLockedErrorMessage().isDisplayed());

    }
}
