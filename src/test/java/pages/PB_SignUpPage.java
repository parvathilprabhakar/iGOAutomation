package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.GenericUtility;

import java.time.Duration;

public class PB_SignUpPage {

    WebDriver driver;
    GenericUtility u;

    By fName = By.id("customer.firstName");
    By lName = By.id("customer.lastName");
    By address = By.id("customer.address.street");
    By city = By.id("customer.address.city");
    By state = By.id("customer.address.state");
    By zipCode = By.id("customer.address.zipCode");
    By phNo = By.id("customer.phoneNumber");
    By ssn = By.id("customer.ssn");
    By username = By.id("customer.username");
    By password = By.id("customer.password");
    By byConfirmPassword = By.id("repeatedPassword");
    By byRegister = By.xpath("//input[@value='Register']");
    By bySignupTitle = By.xpath("//h1[contains(text(),'Signing up is easy')]");
    By byTitleWelcome = By.xpath("//div[@id='rightPanel']/h1[contains(text(),'Welcome')]");

    public PB_SignUpPage(GenericUtility u){
        this.u = u;
        this.driver=u.getDriver();
    }

    public boolean isRegistrationPageDisplayed() throws Exception {
        Thread.sleep(2);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("register"));
        return driver.findElement(bySignupTitle).isDisplayed();
    }
    public void signUpWithDetails(String sFName, String sLName, String sAddress,
                                  String sCity, String sState, String sZipCode,
                                  String sPhNo, String sSSN, String sUsername, String sPassword, String sConfirmPassword ){
            driver.findElement(fName).sendKeys(sFName);
            driver.findElement(lName).sendKeys(sLName);
            driver.findElement(address).sendKeys(sAddress);
            driver.findElement(city).sendKeys(sCity);
            driver.findElement(state).sendKeys(sState);
            driver.findElement(zipCode).sendKeys(sZipCode);
            driver.findElement(phNo).sendKeys(sPhNo);
            driver.findElement(ssn).sendKeys(sSSN);
            driver.findElement(username).sendKeys(sUsername);
            driver.findElement(password).sendKeys(sPassword);
            driver.findElement(byConfirmPassword).sendKeys(sConfirmPassword);
            driver.findElement(byRegister).click();
    }
    public void enterFName(String sFName){
        driver.findElement(fName).sendKeys(sFName);
    }
    public void enterLName(String sLName){
        driver.findElement(lName).sendKeys(sLName);
    }
    public void enterAddress(String sAddress){
        driver.findElement(address).sendKeys(sAddress);
    }
    public void enterCity(String sCity){
        driver.findElement(city).sendKeys(sCity);
    }
    public void enterState(String sState){
        driver.findElement(state).sendKeys(sState);
    }
    public void enterZipCode(String sZipCode){
        driver.findElement(zipCode).sendKeys(sZipCode);
    }
    public void enterPhNo(String sPhNo){
        driver.findElement(phNo).sendKeys(sPhNo);
    }
    public void enterSSN(String sSSN){
        driver.findElement(ssn).sendKeys(sSSN);
    }
    public void enterUsername(String sUsername){
        driver.findElement(username).sendKeys(sUsername);
    }
    public void enterPassword(String sPassword){
        driver.findElement(password).sendKeys(sPassword);
    }
    public void enterConfirmPassword(String sConfirmPassword){
        driver.findElement(byConfirmPassword).sendKeys(sConfirmPassword);
    }
    public void clickRegister(){
        driver.findElement(byRegister).click();
    }
public boolean isUserHomePageDisplayed(){
    try{
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Open New Account']")));
    }catch (TimeoutException e){
        return false;
    }
    return driver.findElement(byTitleWelcome).isDisplayed();
}
    public void validateSuccessfulLogin() {
        if (isUserHomePageDisplayed())
            u.rep.logInReport("PASS", "User successfully logged into the account");
        else
            u.rep.logInReport("FAIL", "Error in Login");
    }
    public void validateSuccessfulRegistration() {
        if (isUserHomePageDisplayed())
            u.rep.logInReport("PASS", "User successfully registered and logged into the account");
        else
            u.rep.logInReport("FAIL", "Error in Registration");
    }

    public void validateRegistrationIsDisplayed() throws Exception {
        if (isRegistrationPageDisplayed())
            u.rep.logInReport("PASS", "User successfully navigated to Registration Page");
        else
            u.rep.logInReport("FAIL", "Unable to navigate to the Registration Page");
    }

}
