package testclasses;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class Test2 extends BaseTest{
  @Test
  public void ftest2() {

	  webDriver.get().get("https://all.testjj9.com/coinfans/skin011/?plat_id=30017&channel_id=30017001#/zh-CN");
	  
	  extentTestThread.get().log(Status.FAIL, "ftest2 Fail");
	  
	  pagecls pls = new pagecls(webDriver.get(),extentTestThread.get(),"Test2222");
	  pls.clickLoginButton();
		 
  }
}
