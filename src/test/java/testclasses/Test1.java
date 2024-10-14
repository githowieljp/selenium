package testclasses;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class Test1 extends BaseTest {
  @Test
  public void ftest1() {

	  
	  
	  webDriver.get().get("https://all.testjj9.com/coinfans/skin011/?plat_id=30017&channel_id=30017001#/zh-CN");
	  
	  extentTestThread.get().log(Status.PASS, "ftest1 PASS");
	  
	  pagecls pls = new pagecls(webDriver.get(),extentTestThread.get(),"Test1111");
	  pls.clickLoginButton();
	 
  }
}
