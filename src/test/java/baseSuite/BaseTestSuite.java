package baseSuite;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageclasses.CoinfansPage;

public class BaseTestSuite {
//	public WebDriver driver;
	public String baseUrl;
//	public WebDriverWait wait;
	
//	public ExtentReports extent;
//	public ExtentSparkReporter spark;
//	public ExtentTest test;
//	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
//	
	private static ExtentReports extentReports;
	protected ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    protected ThreadLocal<ExtentReports> extentReportsThread = new ThreadLocal<>();
    protected ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    
	public GenericMethods gm;
	public String methodName;
	public CoinfansPage coinFansPage;
	public String browser;
	
	@BeforeClass // 每個類之前執行
	@Parameters({"browser"})
	public void beforeClass(String browser) throws IOException {
		baseUrl = "https://all.testjj9.com/coinfans/skin011/?plat_id=30017&channel_id=30017001#/zh-CN";
		this.browser=browser;
		if (browser.equalsIgnoreCase("chrome")) {
			webDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("safari")) {
			webDriver.set(new SafariDriver()); 
		}
		webDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		webDriver.get().manage().window().setSize(new Dimension(1600, 1300));
		webDriver.get().get(baseUrl);
//		driver.get(baseUrl);
//		gm = new GenericMethods(driver);

		
		if (extentReports == null) {
			extentReports = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./reports/CoinfansTest.html");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extentSparkReporter.config().setDocumentTitle("Automated reporting");
            extentSparkReporter.config().setReportName("Automated reporting");
            
//            JsonFormatter json = new JsonFormatter("extent.json");
//            extentReports.createDomainFromJsonArchive("extent.json");
            extentReports.attachReporter(extentSparkReporter);
            
            extentReports.setSystemInfo("Selenium Version", "4.24.0");
            extentReports.setSystemInfo("Platform", "Mac");
            
            
            
//            ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
//            JsonFormatter json = new JsonFormatter("extent.json");
//            ExtentReports extent = new ExtentReports();
//            extent.createDomainFromJsonArchive("extent.json");
//            extent.attachReporter(json, spark);

            
        }
		extentReportsThread.set(extentReports);
//		extentTestThread.set(extentReportsThread.get().createTest(getClass().getSimpleName()));	
//		extentTestThread.get().assignAuthor("Howie");
	}
	
	@AfterClass // 最後執行只一次
	public void afterClass() throws InterruptedException {
	      if (extentReportsThread.get() != null) {
	        	extentReportsThread.get().flush();
	        }
	        if (webDriver.get() != null) {
	        	webDriver.get().quit();
	        }     
	}
	
	@AfterMethod // 每個case後都執行
	public void afterMethod(ITestResult testResult) throws InterruptedException, IOException {
		Thread.sleep(1000);
		if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTestThread.get().log(Status.FAIL,testResult.getThrowable());
			String path = GenericMethods.takeScreenshot(webDriver.get(), testResult.getName());
			extentTestThread.get().log(Status.FAIL, methodName+" 不通過.. "+"截圖如下: ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		if (testResult.getStatus() == ITestResult.SUCCESS) {			
			extentTestThread.get().log(Status.PASS,methodName+" 通過.. ("+testResult.getMethod().getMethodName()+")");
		}
	}
	

	@BeforeSuite // 整個suite只執行一次 只能從xml執行
	public void beforeSuite() {
//		System.out.println("baseSuite >> beforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
//		System.out.println("baseSuite >> afterSuite");
	}

	@BeforeTest // Suite的每個test前都執行 只能從xml執行
	public void beforeTest() {
//		System.out.println("baseSuite >> @BeforeTest");
	}

	@AfterTest
	public void afterTest() {
//		System.out.println("baseSuite >> @afterTest");
	}

}
