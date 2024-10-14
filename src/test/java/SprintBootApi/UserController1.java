package SprintBootApi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.TestNG;

@RestController
@RequestMapping("/api/users")
public class UserController1 {
	@GetMapping
	public String getUsers1() throws SQLException {
//		BetSingleTest bmt = new BetSingleTest(); 
//		bmt.beforeClass();
//		bmt.t1_loginCoinfans("colintest8","@Aa111111");
//		User1 u = new User1("Howie",30);
		  TestNG testNG = new TestNG();
          List<String> suites = new ArrayList<String>();
          suites.add("./testing.xml");
          //suites.add(".\\test-output\\testng-failed.xml");
          testNG.setTestSuites(suites);
          testNG.run();
          
		return "finish";
	}
}
