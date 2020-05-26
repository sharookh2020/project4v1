package com.project_4.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.project_4.utilities.DriverManager;

public class Project4Dashboard extends BasePage{
	
//	public WebDriver driver;
//	public Project4Dashboard(WebDriver driver)
//	{
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//	}
	
	public Project4Dashboard open(String url) {
		
		System.out.println("Page Opened");
		//DriverManager.getDriver().navigate().to(url);
		return (Project4Dashboard) openPage(Project4Dashboard.class);
	}
	
	@FindBy(xpath = "//span[@class='titleHead']")
	@CacheLookup
	WebElement title;

	@FindBy(xpath = "//button[@title='Logout']")
	@CacheLookup
	WebElement btnLogout;
	
//	public Project4LoginPage logout()
//	{
//		btnLogout.click();
//		return new Project4LoginPage(driver);
//	}
	
	public Project4Dashboard logout()
	{
		btnLogout.click();
		return this;
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
