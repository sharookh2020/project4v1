package com.project_4;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project_4.PageObjects.Project4Dashboard;
import com.project_4.PageObjects.Project4LoginPage;
import com.project_4.testcases.BaseTest;
import com.project_4.utilities.DataProviders;
import com.project_4.utilities.DriverManager;

public class TestCase1 extends BaseTest {
	
	Project4LoginPage project4Login;
	Project4Dashboard dashboard;
	
//	private WebDriver driver;
//	
//	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
//	
//	
//	public WebDriver getDriver()
//	{
//		return dr.get();
//	}
//	
//	public void setWebDriver(WebDriver driver)
//	{
//		dr.set(driver);
//	}
	
//	@Test(dataProvider = "getData")
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
//	public void doLogin(String username, String password, String browser)
	public void doLogin(Hashtable<String,String> data)	

	{
//		if(browser.equals("chrome")) {
//			System.out.println("Launching from TC_1 : " + browser);
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
//		driver = new ChromeDriver();
//		
//		}else if(browser.equals("firefox"))
//		{
//			System.out.println("Launching from TC_1 : " + browser);
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		}
		
//		openBrowser(browser);
		openBrowser(data.get("browser"));

		
//		setWebDriver(driver);
		
//		getDriver().manage().window().maximize();
//		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
//		getDriver().get("http://108.61.43.10:8090/admin/dashboard");
		
//		project4Login = new Project4LoginPage(DriverManager.getDriver());
		project4Login = new Project4LoginPage().open("http://108.61.43.10:8090/admin/dashboard");

//		Project4Dashboard dashboard = project4Login.doLogin("jeff", "Jhas8js7j0*5!");
//		Project4Dashboard dashboard = project4Login.doLogin(data.get("username"), data.get("password"));
		project4Login.doLogin(data.get("username"), data.get("password"));

		//dashboard.logout();

		
//		getDriver().findElement(By.id("loginUsername")).sendKeys(username);
//		getDriver().findElement(By.id("loginPassword")).sendKeys(password);
//		getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/form/div/div/div/div[3]/button/span[1]")).click();
		
		
		
//		getDriver().quit();
		//driver.close();
		
		quit();
		
		
	}
	
	@DataProvider(parallel = true)
	public Object[][] getData()
	{
		Object[][] data = new Object[2][3];
		
		data[0][0] = "jeff";
		data[0][1] = "Jhas8js7j0*5!";
		data[0][2] = "chrome";
		
		data[1][0] = "jeff";
		data[1][1] = "Jhas8js7j0*5!";
		data[1][2] = "firefox";
		
		return data;
	}
	
}
