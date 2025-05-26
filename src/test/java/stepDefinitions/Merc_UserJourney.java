package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Merc_PageLogin;
import utility.ExcelUtility;

public class Merc_UserJourney extends _BaseSteps{

    ExcelUtility x = new ExcelUtility("Login");

//    // ************* Mandatory step for all StepDefinition files **********
//    @Given("I prepare for execution")
//    public void iHaveFinalizedTheSetupRequiredForExecution() {
//        initializePagesAndLinks();
//    }
//    // ********************************************************************
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
//        launchTest();
       // launchHomePage();
        u.driver.get("https://demo.guru99.com/test/newtours/");


    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        //new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("login_sucess"));
        u.rep.logInReport("PASS", "User successfully logged in");
    }

    @When("I enter valid credentials username as {string} and password as {string}")
    public void iEnterValidCredentialsUsernameAsAaaAndPasswordAsAaa(String username, String password) {
        oPageLogin = new Merc_PageLogin(u);
        oPageLogin.enterCredentialsAndLogin(username, password);
    }
}
