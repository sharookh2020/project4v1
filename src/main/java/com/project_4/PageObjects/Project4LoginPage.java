package com.project_4.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.project_4.utilities.DriverManager;

public class Project4LoginPage extends BasePage {
	
//	public WebDriver driver;
//	public Project4LoginPage(WebDriver driver)
//	{
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//	}
	
	
	
	@FindBy(id = "loginUsername")
	@CacheLookup
	WebElement txtUsername;

	@FindBy(id = "loginPassword")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/form/div/div/div/div[3]/button/span[1]")
	@CacheLookup
	WebElement btnLogin;
	
	
	public Project4LoginPage open(String url) {
		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (Project4LoginPage) openPage(Project4LoginPage.class);
	}
	
	public void clickLogin() {
		//log.info("clicking login button");
		//btnLogin.click();
		click(btnLogin,"Login button");
	}
	
//	public Project4Dashboard doLogin(String userName, String password)
//	{
//		txtUsername.sendKeys(userName);
//		txtPassword.sendKeys(password);
//		clickLogin();
//		return new Project4Dashboard(driver);	
//	}
	
	public Project4LoginPage doLogin(String userName, String password)
	{
		//txtUsername.sendKeys(userName);
		type(txtUsername, userName, "UserName textbox");
		//txtPassword.sendKeys(password);
		type(txtPassword, password, "Password textbox");
		clickLogin();
		return this;	
	}
	
	public Project4LoginPage doLoginAsInvalidUser(String username, String password)
	{
//		type(txtUsername, username, "Username textbox");
//		type(txtPassword, password, "Password textbox");
//		click(btnLogin, "Sign in Button");

		return this;
	}
	

	public Project4Dashboard doLoginAsValidUser(String username, String password)
	{
//		type(txtUsername, username, "Username textbox");
//		type(txtPassword, password, "Password textbox");
//		click(btnLogin, "Sign in Button");

		return (Project4Dashboard) openPage(Project4Dashboard.class);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(btnLogin);
	}

}
