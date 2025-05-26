package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.ExcelUtility;

public class PB_UserJourney extends _BaseSteps {

    ExcelUtility xRegistration = new ExcelUtility("Registration");

//    // ************* Mandatory step for all StepDefinition files **********
//    @Given("I prepare for execution")
//    public void iHaveFinalizedTheSetupRequiredForExecution() {
//        initializePagesAndLinks();
//    }
//    // ********************************************************************

    @Given("I am on the ParaBank home page")
    public void i_am_on_the_login_page() {
        u.launchAUT();
    }

    @When("I click on Register link")
    public void iClickOnRegisterLink() {
        oPB_LandingPage.clickRegister();
    }

    @Then("I should be redirected to the SignUp Page")
    public void iShouldBeRedirectedToTheSignUpPage() throws Exception {
        oPB_SignUpPage.validateRegistrationIsDisplayed();
    }

    @And("I fill the details and register")
    public void iFillTheDetailsAndRegister() throws Exception {


        oPB_SignUpPage.enterFName(xRegistration.readData("FirstName")); //TODO: Needs to be updated in Constants
        oPB_SignUpPage.enterLName(xRegistration.readData("LastName"));
        oPB_SignUpPage.enterAddress(xRegistration.readData("Address"));
        oPB_SignUpPage.enterCity(xRegistration.readData("City"));
        oPB_SignUpPage.enterState(xRegistration.readData("State"));
        oPB_SignUpPage.enterZipCode(xRegistration.readData("ZipCode"));
        oPB_SignUpPage.enterPhNo(xRegistration.readData("PhoneNumber"));
        oPB_SignUpPage.enterSSN(xRegistration.readData("SSN"));
        oPB_SignUpPage.enterUsername(xRegistration.readData("Username")+System.currentTimeMillis());
        oPB_SignUpPage.enterPassword(xRegistration.readData("Password"));
        oPB_SignUpPage.enterConfirmPassword(xRegistration.readData("ConfirmPassword"));
        u.rep.logInReport();
        oPB_SignUpPage.clickRegister();
        oPB_SignUpPage.validateSuccessfulRegistration();
    }
    @Then("I navigate to all account service pages")
    public void iNavigateToAllAccountServicePages() {
        oPB_UserHome.clickOpenNewAccount();
        oPB_UserHome.clickAccountsOverview();
        oPB_UserHome.clickTransferFunds();
        oPB_UserHome.clickBillPay();
        oPB_UserHome.clickFindTransactions();
        oPB_UserHome.clickUpdateContactInfo();
        oPB_UserHome.clickRequestLoan();
    }
    @And("I logout of my account")
    public void iLogoutOfMyAccount() {
        oPB_UserHome.clickLogout();
    }

    @When("I login to my ParaBank account with {string} and {string}")
    public void iLoginToMyParaBankAccountWithUsernameAndPassword(String username, String password) {
        oPB_LandingPage.enterUsername(username);
        oPB_LandingPage.enterPassword(password);
        oPB_LandingPage.clickLogIn();
        oPB_SignUpPage.validateSuccessfulLogin();
    }
}
