package com.qa.pts.base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.pts.factory.DriverFactory;
import com.qa.pts.pages.HomePage;
import com.qa.pts.pages.LoginPage;
import com.qa.pts.pages.TimeEntriesPage;
import com.qa.pts.pages.UsersPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected TimeEntriesPage timeEntryPage;
	protected UsersPage usersPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
