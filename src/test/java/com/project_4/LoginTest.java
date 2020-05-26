package com.project_4;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.project_4.PageObjects.Project4Dashboard;
import com.project_4.PageObjects.Project4LoginPage;
import com.project_4.testcases.BaseTest;
import com.project_4.utilities.Constants;
import com.project_4.utilities.DataProviders;
import com.project_4.utilities.DataUtil;
import com.project_4.utilities.DriverManager;
import com.project_4.utilities.ExcelReader;

public class LoginTest extends BaseTest {
	Project4LoginPage project4Login;
	Project4Dashboard dashboard;
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
//	public void loginTest(String username, String password, String browser)
	public void loginTest(Hashtable<String,String> data)	
	{

		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
		
//		openBrowser(browser);
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		
//		project4Login = new Project4LoginPage(DriverManager.getDriver());
		project4Login = new Project4LoginPage().open("http://108.61.43.10:8090/admin/dashboard");

//		Project4Dashboard dashboard = project4Login.doLogin("jeff", "Jhas8js7j0*5!");
//		Project4Dashboard dashboard = project4Login.doLogin(data.get("username"), data.get("password"));
		
		
		project4Login.doLogin(data.get("username"), data.get("password"));
		logInfo("Username entered as "+data.get("username")+" and Password entered as "+data.get("password"));

		//dashboard.logout();
		
		//Assert.fail("Failing the login test.");
		quit();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		logInfo("Login Test Completed");
		
		//quit();
		
	}

}
