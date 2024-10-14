package baseSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
	WebDriver driver;
	
	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}

	public String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
		Date date = new Date();// 获取当前时间
		return sdf.format(date);
	}

	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		fileName = fileName + ".png";
		String directory = "/Users/user/Desktop/EasybetMaven/Easybet/reports/Screenshots/";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		return destination;
	}

	public WebElement waitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			System.out.println("最常等待了" + timeout + "秒 元素可用");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println("元素在頁面上出現了");

		} catch (Exception e) {
			System.out.println("元素沒有在頁面上出現");
		}
		return element;
	}

	public void clickWhenReady(By locator, int timeout) {
		try {
			WebElement element = null;
			System.out.println("最常等待了" + timeout + "秒 元素可點擊");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			System.out.println("在頁面上點擊了元素");

		} catch (Exception e) {
			System.out.println("元素沒有在頁面出現");
		}
	}

}
