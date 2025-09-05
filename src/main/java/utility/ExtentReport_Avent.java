package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExtentReport_Avent {
    static ExtentTest test;
    static ExtentReports report;
    static ExtentSparkReporter sparkReporter;
    GenericUtility u;
    String currentDirectory = System.getProperty("user.dir");
    String reportScreenshotFileName = "", reportPath;

    public ExtentReport_Avent() {
    }

    public void setGenericUtilityObject(GenericUtility u) {
        this.u = u;
    }

    public void setReportName(String reportScreenshotFileName) {
        this.reportScreenshotFileName = reportScreenshotFileName;
    }

    public String initiateExtentReport() {
        System.out.println("initiated Extent Report");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        if (reportScreenshotFileName.equals(""))
            reportScreenshotFileName = LocalDate.now().format(formatter) + "_" + System.currentTimeMillis();
        reportPath = currentDirectory + "\\ExtentReport\\" + reportScreenshotFileName + "\\Results.html";
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            reportPath = reportPath.replace("\\", "/");

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().thumbnailForBase64(true);
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
        return reportScreenshotFileName;
    }

    public void startTest(String classname) {
        test = report.createTest(classname);
    }

    public void logInReport(String status, String details) {
        String screenshotPath = u.takeScreenshot();
        MediaEntityBuilder mb = MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath);
        switch (status.toUpperCase()) {
            case "PASS":
                test.log(Status.PASS, details);
                test.log(Status.PASS, mb.build());
                break;
            case "FAIL":
                test.log(Status.FAIL, details);
                test.log(Status.FAIL, mb.build());
                break;
            case "SKIP":
                test.log(Status.SKIP, details);
                test.log(Status.SKIP, mb.build());
                break;
            default:
            case "INFO":
                test.log(Status.INFO, details);
                test.log(Status.INFO,mb.build());
                break;
        }
    }


    public void logInReport() {
        test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(u.takeScreenshot(),"").build());
    }

    public void logInReport(String details) {
        test.log(Status.INFO, details);
        test.log(Status.INFO,  MediaEntityBuilder.createScreenCaptureFromBase64String(u.takeScreenshot()).build());
    }

    public void logInReport(Boolean isScreenshotRequired, String details) {
        if (isScreenshotRequired){
            test.log(Status.INFO, details);
            test.log(Status.INFO,  MediaEntityBuilder.createScreenCaptureFromBase64String(u.takeScreenshot()).build());
    }
        else
            test.log(Status.INFO, details);
    }

    public void terminateExtentReport() {
        report.flush();
    }
}
