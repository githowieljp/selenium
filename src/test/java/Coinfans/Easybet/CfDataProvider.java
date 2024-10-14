package Coinfans.Easybet;

import org.testng.annotations.DataProvider;

public class CfDataProvider {

  @DataProvider(name="CfDp")
  public Object[][] getUserNameAndPwd() {
    return new Object[][] {
    	{"colintest","@Aa111111"},
    	{"colintest1","@Aa111111"},
    	{"colintest2","@Aa111111"},
    	{"colintest3","@Aa111111"},
    	{"colintest4","@Aa111111"},
    	{"colintest5","@Aa111111"},
    	{"colintest6","@Aa111111"},
    	{"colintest7","@Aa111111"},
    	{"colintest8","@Aa111111"},
    	{"colintest9","@Aa111111"}
    };
  }
}
