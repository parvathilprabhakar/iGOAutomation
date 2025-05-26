package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utility.ThreadManager;
import utility.ExtentReport;
import utility.GenericUtility;
import utility.ReadPropFile;

public class Hooks {
    static ThreadManager threadManager;

    static ExtentReport rep;
    static WebDriver driver;
    static String reportScreenshotFileName="";
    static GenericUtility u;
    static ReadPropFile prop = new ReadPropFile();

    @Before
    public void setup(Scenario scenario) throws Exception {
        System.out.println("Hooks---Before");
        threadManager = new ThreadManager();
        String browser = prop.getRunConfig().getProperty("browser");
        threadManager.setDriver(browser);
        driver = threadManager.getDriver();

        u = new GenericUtility(driver, rep);
        threadManager.setGenericObjects(u);

        rep.startTest(scenario.getName());
        rep.logInReport(false, "Browser: " + browser);

    }

    @After
    public void after() {
        System.out.println("Hooks---After");
        threadManager.getDriver().quit();

    }

    @BeforeAll
    public static void beforeAllScenarios() throws Exception {
        System.out.println("Hooks---BeforeAll");
        rep = new ExtentReport();
        reportScreenshotFileName = rep.initiateExtentReport();
        rep.setReportName(reportScreenshotFileName);

    }

    @AfterAll
    public static void afterAllScenarios() {
        System.out.println("Hooks---AfterAll");
        rep.terminateExtentReport();
    }


}
