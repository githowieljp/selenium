package pageclasses;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Coinfans.Easybet.DatabaseQuery;
import baseSuite.GenericMethods;

public class CoinfansPage {

	WebDriver driver;
	ExtentTest test;
	GenericMethods gm;
	
	public CoinfansPage(WebDriver driver, ExtentTest test) {
		this.driver=driver;
		this.test=test;
		gm = new GenericMethods(driver);
	}
	
	// 右上登入按鈕
	public void clickLoginButton() {
		WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'登录')])[1]"));
		element.click();
		test.log(Status.INFO, "點擊登入按鈕");
	}

	// 登入視窗-帳號
	public void inputLoginAccount(String a) {
		WebElement element = driver.findElement(By.xpath("//input[@id='component-2']"));
		element.sendKeys(a);
		test.log(Status.INFO, "輸入登入帳號:"+a);
	}

	// 登入視窗-密碼
	public void inputLoginPassword(String p) {
		WebElement element = driver.findElement(By.xpath("//input[@class='rounded-0' and @type='password']"));
		element.sendKeys(p);
		test.log(Status.INFO, "輸入登入密碼");
	}

	// 登入視窗-確認登入按鈕
	public void clickConfirmLoginButton() {
		WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'登录')])[2]"));
		element.click();
		test.log(Status.INFO, "點擊確認登入");
	}

	// 取得使用者ID
	public String getUserId() {
		WebElement element = driver.findElement(By.xpath("//div[@class='text-14 d-flex flex-column']/div"));
		test.log(Status.INFO, "取得使用者ID");
		return element.getText();
	}
	
	//確認使用者ID是否正確 (DB)
	public void checkUserIdisCorrect(String username) throws SQLException {
		DatabaseQuery dt = new DatabaseQuery();
		String dataFromDB;
		String dataFromUI;
		dataFromUI=getUserId();
		dataFromDB=dt.QueryUserId("select user_id from plat_users where username='"+username+"'");
		Assert.assertEquals(dataFromDB, dataFromUI);
		test.log(Status.INFO, "確認使用者ID是否正確 DB:"+dataFromDB+" UI:"+dataFromUI);
	}
	
	//div[@class='text-14 d-flex flex-column']/div
	
	
	// 幣種選單
	public void clickCoinList() {
		WebElement element = driver.findElement(By.xpath("(//div[@role='button'])[2]"));
		element.click();
		test.log(Status.INFO, "點擊幣種選單");
	}

	// 幣種選單-單一幣種
	public void clickCoin(String coin) {
		WebElement element = driver.findElement(By.xpath("//div[@class='ml-3']/div[contains(text(),'" + coin + "')]"));
		element.click();
		test.log(Status.INFO, "切換成"+coin+"幣種");
	}

	// 菜單-足球
	public void clickMenuSport() {
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'足球')]"));
		element.click();
		driver.switchTo().frame("gameFrame");
		test.log(Status.INFO, "點擊菜單足球，並切換frame");
	}

	// Easybet-菜單-早盤
	public void clicktMenuFuture() {
		WebElement element = driver.findElement(By.xpath("(//div[@class='v-list-item-title' and contains(text(),'早盘')])[1]"));
		element.click();
		test.log(Status.INFO, "點擊菜單-早盤");
	}

	// Easybet-第一個盤口
	public void clickBetOption1() {
		WebElement element = driver.findElement(
				By.xpath("((//*[contains(@class,'ma-0 mb-2')])[2]//*[contains(@class,'selection-box')])[1]"));
		element.click();
		test.log(Status.INFO, "點擊第一個盤口");
	}

	// Easybet-第二個盤口
	public void clickBetOption2() {
		WebElement element = driver.findElement(
				By.xpath("((//*[contains(@class,'ma-0 mb-2')])[3]//*[contains(@class,'selection-box')])[1]"));
		element.click();
		test.log(Status.INFO, "點擊第二個盤口");
	}

	// Easybet-串關頁籤
	public void clickMultipleTab() {
		gm.clickWhenReady(By.xpath("//button[@value='multi']/span[@class='v-btn__content']"), 20);
//		WebElement element = driver.findElement(By.xpath("//button[@value='multi']/span[@class='v-btn__content']"));
//		element.click();
		test.log(Status.INFO, "點擊串關頁籤");
	}

	// Easybet-下注金額輸入框-單注
	public void clickSingleBetAmountInput() throws InterruptedException {
		Thread.sleep(2000);
		gm.clickWhenReady(By.xpath("(//div[contains(@class,'bet-box')]//div[contains(text(),'USD')]/following-sibling::div)[5]"), 10);
		test.log(Status.INFO, "點擊下注金額輸入框");
	}

	// Easybet-下注金額輸入框-串關
	public void clickMultipleBetAmountInput() throws InterruptedException {
		Thread.sleep(2000);
		gm.clickWhenReady(By.xpath("(//div[contains(@class,'bet-box')]//div[contains(text(),'USD')]/following-sibling::div)[1]"), 30);
//		gm.waitForElement(By.xpath("(//div[contains(@class,'bet-box')]//div[contains(text(),'USD')]/following-sibling::div)[1]"), 30).click();
		test.log(Status.INFO, "點擊下注金額輸入框");
	}

	// Easybet-單注-鍵盤1
	public void clickAmountKb1() {
		WebElement element = driver.findElement(By.xpath("(//div[contains(@class,'v-col v-col-2')])[1]"));
		element.click();
		test.log(Status.INFO, "點擊下注金額的鍵盤：1");
	}

	// Easybet-單注-鍵盤0
	public void clickAmountKb0() {
		WebElement element = driver.findElement(By.xpath("(//div[contains(@class,'v-col v-col-2')])[11]"));
		element.click();
		test.log(Status.INFO, "點擊下注金額的鍵盤：0");
	}

	// Easybet-單注-下注
	public void clickBetButton() {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'bet-hover')]"));
		element.click();
		test.log(Status.INFO, "點擊下注按鈕");
	}

	// Easybet-下注後訂單確認窗口
	public void clickBetConfirmWindow() {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'bet-box')]//div[contains(text(),'订单确认')]"));
		element.click();
	}

	// Easybet-單注-下注後完成
	public void clickFinishButton() {
		//等確認窗口
		gm.waitForElement(By.xpath("//div[contains(@class,'bet-box')]//div[contains(text(),'订单确认')]"), 10);
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'完成')]"));
		element.click();
		test.log(Status.INFO, "點擊下注完成");
	}

}
