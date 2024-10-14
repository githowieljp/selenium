package testclasses;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class pagecls {

	WebDriver driver;
	ExtentTest test;
	String st;

	
	public pagecls(WebDriver driver, ExtentTest test, String st) {
		this.driver=driver;
		this.test=test;
		this.st=st;
	}
	
	public void clickLoginButton() {
		test.log(Status.INFO, st);
	}
	
}
