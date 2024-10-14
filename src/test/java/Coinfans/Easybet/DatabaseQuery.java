package Coinfans.Easybet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


// MySQL workbench - for Database
// Driver Download - https://dev.mysql.com/downloads/connector/j/

public class DatabaseQuery {
	// Connection 对象
	static Connection conn = null;
	// Statement 对象
	private static Statement stmt= null;
	// 结果集
	private static ResultSet results = null;
	// 数据库 URL常量
	public static String DB_URL = "jdbc:mysql://34.96.218.9:3306/apiuniversedb_hk";//Oracle "jdbc:oracle:thin:@localhost:1521/sid"

	// 数据库用户名
	public static String DB_USER = "andesen";
	// 数据库密码
	public static String DB_PASSWORD = "aezahf2tho2wiew-";
	// Driver
	public static String driver = "com.mysql.cj.jdbc.Driver";//oracle.jdbc.driver.OracleDriver
	
	// WebDriver
//	 public static WebDriver dv;
	
	
	@BeforeMethod
	public void beforeMethod() {
		// 初始化 WebDriver
//		 dv = new FirefoxDriver();
		
		// Properties for creating connection to database
		Properties props = new Properties();
		props.setProperty("user", DB_USER);
		props.setProperty("password", DB_PASSWORD);
	    
		try {
			// STEP 1: 注册 JDBC driver
			Class.forName(driver).getDeclaredConstructor().newInstance();
			
			// STEP 2: 连接数据库
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//			conn = DriverManager.getConnection(DB_URL, props);

			System.out.println("Connected database successfully...");
			
			// STEP 3: Statement object to send the SQL statement to the Database
			System.out.println("Creating statement...");
		     stmt = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String QueryUserId(String sqlStmt) throws SQLException {
		
			 beforeMethod();
		String user_id = null;
        String query = sqlStmt;
        System.out.println(query);
        try {
        	// STEP 4: 从结果集里提取数据
        	results = stmt.executeQuery(query);
        	while (results.next()) {
//        		int gold = results.getInt("gold");
        		user_id = results.getString("user_id");
//        		String plat_name = results.getString("plat_name");
//        		String currency_type = results.getString("currency_type");
//        		
        		//显示值
        		System.out.println("user_id"+user_id);
//        		System.out.println("plat_id"+plat_id);
//        		System.out.println("plat_name"+plat_name);
//        		System.out.println("currency_type"+currency_type);
//        		
        		//来自GUI
//        		WebElement element = dv.findElement(By.id("uid"));
//        		String actrualUserName = element.getText();
//        		Assert.assertEquals(actrualUserName, first);
  
            }
        	results.close();
		} catch (SQLException se) {
			// 处理 JDBC errors
			se.printStackTrace();
			return user_id;
		} catch (Exception e) {
			// 处理 Class.forName errors
			e.printStackTrace();
			return user_id;
		}
    	afterMethod();
    	return user_id;
	}

	@AfterMethod
	public void afterMethod() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}