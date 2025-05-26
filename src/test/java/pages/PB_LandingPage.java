package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import utility.GenericUtility;

public class PB_LandingPage {

    WebDriver driver;
    GenericUtility u;

    By registerLink = By.xpath("//a[text()='Register']");
    By username = By.name("username");
    By password = By.name("password");
    By login = By.xpath("//input[@value='Log In']");
    By submit = By.name("submit");

    public PB_LandingPage(GenericUtility u){
        this.u = u;
        this.driver=u.getDriver();
    }
    public void clickRegister(){
        driver.findElement(registerLink).click();
    }
    public void enterUsername(String sUsername){
        driver.findElement(username).sendKeys(sUsername);
    }
    public void enterPassword(String sPassword){
        driver.findElement(password).sendKeys(sPassword);
    }
    public void clickLogIn(){
        driver.findElement(login).click();
    }



}
