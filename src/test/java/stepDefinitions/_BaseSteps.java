package stepDefinitions;

import pages.Merc_PageLogin;
import pages.PB_LandingPage;
import pages.PB_SignUpPage;
import pages.PB_UserHome;
import utility.GenericUtility;
import utility.ReadPropFile;
import utility.ThreadManager;

public class _BaseSteps {

    ReadPropFile prop = new ReadPropFile();
   // String homeURL= prop.getCredData().getProperty("URL");
   // ExtentReport rep;
    GenericUtility u= ThreadManager.getGenericUtility();
    Merc_PageLogin oPageLogin = new Merc_PageLogin(u);
    PB_LandingPage oPB_LandingPage = new PB_LandingPage(u);
    PB_SignUpPage oPB_SignUpPage= new PB_SignUpPage(u);
    PB_UserHome oPB_UserHome = new PB_UserHome(u);

}
