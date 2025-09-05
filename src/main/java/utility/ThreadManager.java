package utility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ThreadManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<GenericUtility> uThread = new ThreadLocal<>();


    //-------------------------- Driver -------------------------------
    public static WebDriver setDriver(String browser) throws Exception {
        System.out.println("Driver set to: "+browser);
        String currentDirectory = System.getProperty("user.dir");
        if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currentDirectory + "\\drivers\\geckodriver.exe");
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentDirectory + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("--start-maximized");
		    options.addArguments("Zoom 80%");
			options.addArguments("--remote-allow-origins=*");
			driver.set(new ChromeDriver(options));
//			WebDriverManager.chromedriver().setup();
//			WebDriver chromeDriver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			//System.setProperty("webdriver.edge.driver", currentDirectory + "\\drivers\\MicrosoftWebDriver.exe");
			driver.set(new EdgeDriver());
		} else {
			throw new Exception("Browser is not correct");
		}
		driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriver drivera= driver.get();
        return driver.get();
    }
    public static synchronized WebDriver getDriver() {
        return driver.get();
    }


    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    //------------------- Misc (Needs relocation) -----------------------------------------
    public static void setGenericObjects(GenericUtility u ){
        uThread.set(u);
    }

    public static synchronized GenericUtility getGenericUtility(){
       return uThread.get();
    }

}
