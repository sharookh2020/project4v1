package com.project_4.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.project_4.ExtentListeners.ExtentListeners;
import com.project_4.utilities.DriverFactory;
import com.project_4.utilities.DriverManager;

public class BaseTest {
	
	/**
	 * Using Grid : WebDrive change to RemoteWebDriver
	 * @return
	 */
	private WebDriver driver;	
//	private RemoteWebDriver driver;	
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public boolean grid=false;
	private String defaultUserName;
	private String defaultPassword;
	
	@BeforeSuite
	public void setUpFramework()
	{
		configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
	
		
        if (System.getProperty("os.name").equalsIgnoreCase("mac")) {
        	
        	DriverFactory.setChromeDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
    		DriverFactory.setGeckoDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
    	
        }else {
		
		
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");

        }
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
public void logInfo(String message) {
		
		ExtentListeners.testReport.get().info(message);
	}
	
	public void configureLogging()
	{
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
				+ "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
//	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	
	
	public WebDriver getDriver()
	{
		return dr.get();
	}
	
	public void setWebDriver(WebDriver driver)
//	public void setWebDriver(RemoteWebDriver driver)
	{
		dr.set(driver);
	}
	
	
	public void openBrowser(String browser)
	{
		
if(System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equals("Grid")) {
			
	/*
	 * If grid=true, test run on Grid.
	 * If grid=false, test directly run browsers on that machine
	 */
			//grid=true;
			grid=false;

			
		}
		

		DriverFactory.setRemote(grid);

		if (DriverFactory.isRemote()) {
			
			log.info("Test run on Grid.");
			
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
				log.info("Starting the session on Grid !!!");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else
		{
			log.info("Test directly run browsers on that machine.");
			
			if (browser.equals("chrome")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.chrome.driver",DriverFactory.getChromeDriverExePath());
				driver = new ChromeDriver();
				log.info("Chrome browser launched...!!!");
			} else if (browser.equals("firefox")) {
				System.out.println("Launching : " + browser);
				System.setProperty("webdriver.gecko.driver",DriverFactory.getGeckoDriverExePath());
				driver = new FirefoxDriver();
				log.info("Firefox browser launched...!!!");
			}
			
			DriverManager.setWebDriver(driver);
			log.info("Driver Initialized !!!");
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			setDefaultUserName(Config.getProperty("defaultUserName"));
//			setDefaultPassword(Config.getProperty("defaultPassword"));
		}
		
		
		
//		if(browser.equals("chrome")) {
//			System.out.println("Launching : " + browser);
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
//		driver = new ChromeDriver();
//		
//		}else if(browser.equals("firefox"))
//		{
//			System.out.println("Launching : " + browser);
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		}
		
		
		
//		DesiredCapabilities cap = null;
//		
//		if(browser.equals("chrome"))
//		{
//			cap = DesiredCapabilities.chrome();
//			cap.setBrowserName("chrome");
//			cap.setPlatform(Platform.ANY);
//		}else if(browser.equals("firefox"))
//		{
//			cap = DesiredCapabilities.firefox();
//			cap.setBrowserName("firefox");
//			cap.setPlatform(Platform.ANY);
//		}
//		
//		try {
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		DriverManager.setWebDriver(driver);
//		DriverManager.getDriver().manage().window().maximize();
//		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
//		//DriverManager.getDriver().get("http://108.61.43.10:8090/admin/dashboard");
		
	}
	
	public void quit()
	{
		DriverManager.getDriver().quit();
	}
	

}
