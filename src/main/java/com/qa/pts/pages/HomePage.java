package com.qa.pts.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ElementUtil;

public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.xpath("//div[@role=\"button\"]//p[text()=\"Dashboard\"]");
	private By homePageHeaders = By.xpath("//div[@role=\"button\"]//p");
	private By timesheetsTab = By.xpath("//h6[text()=\"Timesheets\"]/parent::div");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getHomePageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.HOME_PAGE_TITLE_VALUE);
		System.out.println("Home Page Title is: " + title);
		return title;
	}
	
	public String getHomePageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.HOME_PAGE_URL_FRACTION_VALUE);
		System.out.println("Home Page Url is: " + url);
		return url;
	}
	
	public boolean isLogoutLinkExists() {
		return eleUtil.waitForElementPresence(logoutLink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public boolean isTimesheetsTabExists() {
		return eleUtil.waitForElementPresence(timesheetsTab, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getHeadersList() {
		List<WebElement> headersList = driver.findElements(homePageHeaders);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public TimeEntriesPage goToTimesheets() {
		if(isTimesheetsTabExists()) {
			eleUtil.doClick(timesheetsTab);
			return new TimeEntriesPage(driver);
		}
		else {
			System.out.println("Timesheets Tab is not present on the page....");
			return null;
		}
		
	}
	
	

}
