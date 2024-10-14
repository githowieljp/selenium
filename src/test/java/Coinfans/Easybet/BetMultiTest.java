package Coinfans.Easybet;

import java.sql.SQLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseSuite.BaseTestSuite;
import pageclasses.CoinfansPage;

public class BetMultiTest extends BaseTestSuite {

	@BeforeClass // 剛開始執行只一次
	public void beforeClass() {
//		test = report.startTest(gm.getNowTime()+" Easybet-下注測試-串關-2關");
		extentTestThread.set(extentReportsThread.get().createTest("Easybet-下注測試-串關 "+getClass().getSimpleName()));	
		coinFansPage = new CoinfansPage(webDriver.get(),extentTestThread.get());
		extentTestThread.get().log(Status.INFO, "WebDriver初始化..");
		extentTestThread.get().log(Status.INFO, "使用"+browser +"打開Coinfans..");

	}

	@Test // (dataProvider = "CfDp", dataProviderClass = CfDataProvider.class)
	@Parameters({"username","password"})
	public void t1_loginCoinfans(String username, String password) throws SQLException {
		methodName = "驗證:登入Coinfans";
		coinFansPage.clickLoginButton();
		coinFansPage.inputLoginAccount(username);
		coinFansPage.inputLoginPassword(password);
		coinFansPage.clickConfirmLoginButton();
//		coinFansPage.checkUserIdisCorrect(username);

//		SoftAssert saObj = new SoftAssert();
//		saObj.assertEquals(userId, "300363471");
//		saObj.assertAll();
	}

	@Test
	public void t2_switchCoin() throws Exception {	
		methodName = "驗證:切換幣種";
		coinFansPage.clickCoinList();
		coinFansPage.clickCoin("USDT");
	}
	
	@Test
	public void t3_openEasybet() {
		methodName = "驗證:打開體育";
		coinFansPage.clickMenuSport();
		coinFansPage.clicktMenuFuture();
	}

	@Test
	public void t4_betSingle() throws InterruptedException {
		methodName = "驗證:串關下注";
		coinFansPage.clickBetOption1();
		coinFansPage.clickBetOption2();
		coinFansPage.clickMultipleTab();
		coinFansPage.clickMultipleBetAmountInput();
		coinFansPage.clickAmountKb1();
		coinFansPage.clickAmountKb0();
		coinFansPage.clickBetButton();
		coinFansPage.clickFinishButton();
	}

}
