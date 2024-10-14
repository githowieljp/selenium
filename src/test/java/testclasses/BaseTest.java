package testclasses;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	private static ExtentReports extentReports;
	
	protected ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    protected ThreadLocal<ExtentReports> extentReportsThread = new ThreadLocal<>();
    protected ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	
	
//	public static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
//	public ExtentSparkReporter spark1;
//	public ExtentTest extentTest1;
	

	@BeforeClass
	public void beforeMethod() {
		
		webDriver.set(new ChromeDriver());;
		webDriver.get().manage().window().setSize(new Dimension(1600, 1300));
		
		
		if (extentReports == null) {
			extentReports = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./reports/aaaa.html");
            extentReports.attachReporter(htmlReporter);
        }
		extentReportsThread.set(extentReports);
		extentTestThread.set(extentReportsThread.get().createTest(getClass().getSimpleName()));
	}
	
    @AfterClass
    public void tearDown() {
        if (extentReportsThread.get() != null) {
        	extentReportsThread.get().flush();
        }
        if (webDriver.get() != null) {
        	webDriver.get().quit();
        }
    }
	
	
//    public WebDriver getDriver() {
//        return driver.get();
//    }
    
//    public ExtentTest getTest() {
//        return extentTestThread.get();
//    }
	
	
}
