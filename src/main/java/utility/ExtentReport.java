package utility;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExtentReport {
    static ExtentTest test;
    static ExtentReports report;
    GenericUtility u;
    String currentDirectory = System.getProperty("user.dir");
    String reportScreenshotFileName="",reportPath;

//    public ExtentReport(GenericUtility u) {
//        this.u = u;
//    }

    public ExtentReport() {

    }


    public void setGenericUtilityObject(GenericUtility u){
        this.u=u;
    }
    public void setReportName(String reportScreenshotFileName) {
        this.reportScreenshotFileName=reportScreenshotFileName;
        //reportPath = currentDirectory + "\\ExtentReport\\" + reportScreenshotFileName;
    }
    public String initiateExtentReport() {
        System.out.println("initiated Extent Report");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if(reportScreenshotFileName.equals(""))
            reportScreenshotFileName = LocalDate.now().format(formatter) + "_" + System.currentTimeMillis();
        reportPath = currentDirectory + "\\ExtentReport\\" + reportScreenshotFileName + "\\Results.html";
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            reportPath = reportPath.replace("\\", "/");
        report = new ExtentReports(reportPath);
        return reportScreenshotFileName;
    }

    public void startTest(String classname) {//startReport
        test = report.startTest(classname);
    }

    public void logInReport(String status, String details ) {

        switch (status.toUpperCase()) {
            case "PASS":
                test.log(LogStatus.PASS,  test.addScreenCapture(u.takeScreenshot())+ details);
                break;
            case "FAIL":
                test.log(LogStatus.FAIL, test.addScreenCapture(u.takeScreenshot()) + details);
                break;
            case "SKIP":
                test.log(LogStatus.SKIP, /* test.addScreenCapture(u.takeScreenshot())+ */details);
                break;
            default:
            case "INFO":
                test.log(LogStatus.INFO, details);
                break;
        }
    }
    public void logInReport() {//logTest
        test.log(LogStatus.INFO, test.addScreenCapture(u.takeScreenshot()));
    }
    public void logInReport(String details) {//logTest
        test.log(LogStatus.INFO, test.addScreenCapture(u.takeScreenshot())+details);
    }
    public void logInReport(Boolean isScreenshotRequired, String details) {//logTest
     if(isScreenshotRequired)   test.log(LogStatus.INFO, test.addScreenCapture(u.takeScreenshot())+details);
     else test.log(LogStatus.INFO, details);
    }

    public void terminateExtentReport() {//endReport()
        report.endTest(test);
        report.flush();
    }


}
