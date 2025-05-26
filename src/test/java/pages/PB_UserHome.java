package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.GenericUtility;

import java.time.Duration;

public class PB_UserHome {

    WebDriver driver;
    GenericUtility u;

    By fName = By.id("customer.firstName");
    By lName = By.id("customer.lastName");

    public PB_UserHome(GenericUtility u){
        this.u = u;
        this.driver=u.getDriver();
    }


/*public boolean isUserHomePageDisplayed(){
    try{
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Open New Account']")));
    }catch (TimeoutException e){
        return false;
    }
    return driver.findElement(byTitleWelcome).isDisplayed();
}*/

public void clickOpenNewAccount(){
        u.click(By.xpath("//a[text()='Open New Account']"));
        u.rep.logInReport("Clicked Open New Account");
}
public void clickAccountsOverview(){
        u.click(By.xpath("//a[text()='Accounts Overview']"));
        u.rep.logInReport("Clicked Accounts Overview");
}
public void clickTransferFunds(){
        u.click(By.xpath("//a[text()='Transfer Funds']"));
        u.rep.logInReport("Transfer Funds");
}
public void clickBillPay(){
        u.click(By.xpath("//a[text()='Bill Pay']"));
        u.rep.logInReport("Clicked Bill Pay");
}
public void clickFindTransactions(){
        u.click(By.xpath("//a[text()='Find Transactions']"));
        u.rep.logInReport("Clicked Find Transactions");
}
public void clickRequestLoan(){
        u.click(By.xpath("//a[text()='Request Loan']"));
        u.rep.logInReport("Clicked Request Loan");
}
public void clickUpdateContactInfo(){
        u.click(By.xpath("//a[text()='Update Contact Info']"));
        u.rep.logInReport("Clicked Update Contact Info");
}
public void clickLogout(){
        u.click(By.xpath("//a[text()='Log Out']"));
        u.rep.logInReport("Clicked Logout");
}


}
