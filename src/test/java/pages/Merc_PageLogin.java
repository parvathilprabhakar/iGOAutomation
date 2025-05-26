package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import utility.GenericUtility;

public class Merc_PageLogin {

    WebDriver driver;
    GenericUtility u;

    By username = By.name("userName");
    By password = By.name("password");
    By submit = By.name("submit");

    public Merc_PageLogin(GenericUtility u){
        this.u = u;
        this.driver=u.getDriver();
    }
    public void enterCredentialsAndLogin(String uName, String pwd){
        driver.findElement(username).sendKeys(uName);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(submit).click();
    }
}
