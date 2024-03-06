package com.qa.pts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
private WebDriver driver;
private ElementUtil eleUtil;
	
	//1. Private By Locators
	private By emailId = By.name("email");
	private By proceedBtn = By.xpath("//div/button[@type=\"submit\"]");
	
	//2. page constructor...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. page methods or actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page title is: " + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login Page url is: " + url);
		return url;
	}
	
	public boolean isProceedBtnLinkExist() {
		return eleUtil.waitForElementVisible(proceedBtn, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	@Step("login with Username : {0}")
	public HomePage doLogin(String un) {
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(proceedBtn).click();
		
		eleUtil.waitForElementPresence(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doClick(proceedBtn);
		YopMailPage yp = new YopMailPage(driver);
		yp.mailLogin(un);
		
		return new HomePage(driver);
	}

}
