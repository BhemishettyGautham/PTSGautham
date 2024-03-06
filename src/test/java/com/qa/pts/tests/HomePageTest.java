package com.qa.pts.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.pts.base.BaseTest;
import com.qa.pts.constants.AppConstants;

public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim());
	}
	
	@Test(priority = 1)
	public void homePageTitleTest() {
		String actTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void homePageUrlTest() {
		String actUrl = homePage.getHomePageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.HOME_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void isLogoutLinkExistsTest() {
		Assert.assertTrue(homePage.isLogoutLinkExists());
	}
	
	@Test(priority = 4)
	public void homePageHeadersCountTest() {
		List<String> actPageHeadersList = homePage.getHeadersList();
		System.out.println("Actual Home Page Headers: " + actPageHeadersList);
		Assert.assertEquals(actPageHeadersList.size(), AppConstants.HOME_PAGE_HEADERS_COUNT);
	}
	
	@Test(priority = 5)
	public void homePageHeadersValueTest() {
		List<String> actPageHeadersList = homePage.getHeadersList();
		System.out.println("Actual Home Page Headers: " + actPageHeadersList);
		Assert.assertEquals(actPageHeadersList, AppConstants.HOME_PAGE_EXPECTED_HEADERS_LIST);
	}
	
	@Test(priority = 6)
	public void goToTimesheetsTest() {
		timeEntryPage = homePage.goToTimesheets();
		Assert.assertEquals(timeEntryPage.getTimeEntryPageTitle(), AppConstants.TIMEENTRY_PAGE_TITLE_VALUE);
	}

}
